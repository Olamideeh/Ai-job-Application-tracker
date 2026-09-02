package com.olamide.ai.job.application.tracker.service;

import com.olamide.ai.job.application.tracker.dto.JobApplicationRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobApplicationResponseDto;
import com.olamide.ai.job.application.tracker.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobApplicationService {

    JobApplicationResponseDto createApplication(
            JobApplicationRequestDto request
    );

    Page<JobApplicationResponseDto> getAll(Pageable pageable);

    List<JobApplicationResponseDto> findByEmail();

    JobApplicationResponseDto getById(Long id);

    JobApplicationResponseDto update(
            Long id,
            JobApplicationRequestDto request
    );

    void delete(Long id);

    JobApplicationResponseDto updateStatus(
            Long id,
            ApplicationStatus status
    );
    Page<JobApplicationResponseDto> findByStatus(ApplicationStatus status, Pageable pageable);
}
