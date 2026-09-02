package com.olamide.ai.job.application.tracker.service;

import com.olamide.ai.job.application.tracker.dto.JobApplicationRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobApplicationResponseDto;
import com.olamide.ai.job.application.tracker.entity.JobApplication;
import com.olamide.ai.job.application.tracker.entity.User;
import com.olamide.ai.job.application.tracker.enums.ApplicationStatus;
import com.olamide.ai.job.application.tracker.exception.ApplicationNotFoundException;
import com.olamide.ai.job.application.tracker.repository.JobApplicationRepository;
import com.olamide.ai.job.application.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    @Override
    public JobApplicationResponseDto createApplication(
            JobApplicationRequestDto request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        JobApplication application = new JobApplication();

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setLocation(request.getLocation());
        application.setJobUrl(request.getJobUrl());
        application.setStatus(request.getStatus());
        application.setApplicationDate(request.getApplicationDate());
        application.setNotes(request.getNotes());

        application.setUser(user);

        JobApplication savedApplication =
                jobApplicationRepository.save(application);

        return new JobApplicationResponseDto(
                savedApplication.getId(),
                savedApplication.getCompanyName(),
                savedApplication.getJobTitle(),
                savedApplication.getLocation(),
                savedApplication.getJobUrl(),
                savedApplication.getStatus(),
                savedApplication.getApplicationDate(),
                savedApplication.getNotes()
        );
    }
    @Override
    public Page<JobApplicationResponseDto> getAll(Pageable pageable) {

        Page<JobApplication> applications =
                jobApplicationRepository.findAll(pageable);

        return applications.map(application ->
                new JobApplicationResponseDto(
                        application.getId(),
                        application.getCompanyName(),
                        application.getJobTitle(),
                        application.getLocation(),
                        application.getJobUrl(),
                        application.getStatus(),
                        application.getApplicationDate(),
                        application.getNotes()
                )
        );
    }

    @Override
    public List<JobApplicationResponseDto> findByEmail() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        List<JobApplication> applications =
                jobApplicationRepository.findByUserEmail(email);

        return applications.stream()
                .map(application -> new JobApplicationResponseDto(
                        application.getId(),
                        application.getCompanyName(),
                        application.getJobTitle(),
                        application.getLocation(),
                        application.getJobUrl(),
                        application.getStatus(),
                        application.getApplicationDate(),
                        application.getNotes()
                ))
                .toList();
    }

    @Override
    public JobApplicationResponseDto getById(Long id) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        JobApplication application =
                jobApplicationRepository.findById(id)
                        .orElseThrow(() ->
                                new ApplicationNotFoundException("Application not found"));

        if (!application.getUser().getEmail().equals(email)) {
            throw new RuntimeException(
                    "You are not authorized to access this application"
            );
        }

        return new JobApplicationResponseDto(
                application.getId(),
                application.getCompanyName(),
                application.getJobTitle(),
                application.getLocation(),
                application.getJobUrl(),
                application.getStatus(),
                application.getApplicationDate(),
                application.getNotes()
        );
    }



    @Override
    public JobApplicationResponseDto update(
            Long id,
            JobApplicationRequestDto request
    ) {

        JobApplication application =
                jobApplicationRepository.findById(id)
                        .orElseThrow(() ->
                                new ApplicationNotFoundException("Application not found"));

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setLocation(request.getLocation());
        application.setJobUrl(request.getJobUrl());
        application.setStatus(request.getStatus());
        application.setApplicationDate(request.getApplicationDate());
        application.setNotes(request.getNotes());

        JobApplication updatedApplication =
                jobApplicationRepository.save(application);


        return new JobApplicationResponseDto(
                updatedApplication.getId(),
                updatedApplication.getCompanyName(),
                updatedApplication.getJobTitle(),
                updatedApplication.getLocation(),
                updatedApplication.getJobUrl(),
                updatedApplication.getStatus(),
                updatedApplication.getApplicationDate(),
                updatedApplication.getNotes()
        );
    }

    @Override
    public void delete(Long id) {

        JobApplication application =
                jobApplicationRepository.findById(id)
                        .orElseThrow(() ->
                                new ApplicationNotFoundException("Application not found"));

        jobApplicationRepository.delete(application);
    }

    @Override
    public JobApplicationResponseDto updateStatus(
            Long id,
            ApplicationStatus status
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        JobApplication application =
                jobApplicationRepository.findById(id)
                        .orElseThrow(() ->
                                new ApplicationNotFoundException("Application not found"));

        if (!application.getUser().getEmail().equals(email)) {
            throw new ApplicationNotFoundException (
                    "You are not authorized to access this application"
            );
        }

        application.setStatus(status);

        JobApplication savedApplication =
                jobApplicationRepository.save(application);

        return new JobApplicationResponseDto(
                savedApplication.getId(),
                savedApplication.getCompanyName(),
                savedApplication.getJobTitle(),
                savedApplication.getLocation(),
                savedApplication.getJobUrl(),
                savedApplication.getStatus(),
                savedApplication.getApplicationDate(),
                savedApplication.getNotes()
        );
    }

    @Override
    public Page<JobApplicationResponseDto> findByStatus(
            ApplicationStatus status,
            Pageable pageable
    ) {
        Page<JobApplication> applications =
                jobApplicationRepository.findByStatus(status, pageable);

        return applications.map(application ->
                new JobApplicationResponseDto(
                        application.getId(),
                        application.getCompanyName(),
                        application.getJobTitle(),
                        application.getLocation(),
                        application.getJobUrl(),
                        application.getStatus(),
                        application.getApplicationDate(),
                        application.getNotes()
                )
        );
    }
}