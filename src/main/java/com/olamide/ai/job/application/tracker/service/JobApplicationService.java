package com.olamide.ai.job.application.tracker.service;

import com.olamide.ai.job.application.tracker.dto.JobApplicationRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobApplicationResponseDto;

import java.util.List;

public interface JobApplicationService {

    JobApplicationResponseDto createApplication(
            JobApplicationRequestDto request
    );
    List<JobApplicationResponseDto> getAll();
    List<JobApplicationResponseDto> findByEmail();

    JobApplicationResponseDto getById(Long id);

    JobApplicationResponseDto update(
            Long id,
            JobApplicationRequestDto request
    );

    void delete(Long id);
}