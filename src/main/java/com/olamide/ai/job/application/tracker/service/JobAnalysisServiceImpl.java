package com.olamide.ai.job.application.tracker.service;

import com.olamide.ai.job.application.tracker.dto.JobAnalysisRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobAnalysisResponseDto;
import com.olamide.ai.job.application.tracker.entity.JobAnalysis;
import com.olamide.ai.job.application.tracker.entity.JobApplication;
import com.olamide.ai.job.application.tracker.exception.ApplicationNotFoundException;
import com.olamide.ai.job.application.tracker.repository.JobAnalysisRepository;
import com.olamide.ai.job.application.tracker.repository.JobApplicationRepository;
import com.olamide.ai.job.application.tracker.service.JobAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobAnalysisServiceImpl implements JobAnalysisService {

    private final JobAnalysisRepository jobAnalysisRepository;
    private final JobApplicationRepository jobApplicationRepository;

    @Override
    public JobAnalysisResponseDto createAnalysis(
            Long applicationId,
            JobAnalysisRequestDto request
    ) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        JobApplication application =
                jobApplicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new ApplicationNotFoundException(
                                        "Job application not found"
                                )
                        );

        if (!application.getUser().getEmail().equals(email)) {
            throw new ApplicationNotFoundException(
                    "Job application not found"
            );
        }
        if (jobAnalysisRepository
                .findByJobApplicationId(applicationId)
                .isPresent()) {

            throw new IllegalStateException(
                    "Analysis already exists for this application"
            );
        }

        JobAnalysis analysis = new JobAnalysis();

        analysis.setJobApplication(application);
        analysis.setJobDescription(request.getJobDescription());
        analysis.setSummary(request.getSummary());
        analysis.setRequiredSkills(request.getRequiredSkills());
        analysis.setMissingSkills(request.getMissingSkills());
        analysis.setRecommendations(request.getRecommendations());
        analysis.setSeniority(request.getSeniority());
        analysis.setMatchScore(request.getMatchScore());

        JobAnalysis savedAnalysis =
                jobAnalysisRepository.save(analysis);

        return JobAnalysisResponseDto.fromEntity(savedAnalysis);
    }

    @Override
    public JobAnalysisResponseDto getAnalysis(
            Long applicationId
    ) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        JobApplication application =
                jobApplicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new ApplicationNotFoundException(
                                        "Job application not found"
                                )
                        );

        if (!application.getUser().getEmail().equals(email)) {
            throw new ApplicationNotFoundException(
                    "Job application not found"
            );
        }

        JobAnalysis analysis =
                jobAnalysisRepository
                        .findByJobApplicationId(applicationId)
                        .orElseThrow(() ->
                                new ApplicationNotFoundException(
                                        "Analysis not found"
                                )
                        );

        return JobAnalysisResponseDto.fromEntity(analysis);
    }
}