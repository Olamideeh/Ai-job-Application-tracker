package com.olamide.ai.job.application.tracker.service;

import com.olamide.ai.job.application.tracker.dto.JobAnalysisRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobAnalysisResponseDto;

public interface JobAnalysisService {

    JobAnalysisResponseDto createAnalysis(
            Long applicationId,
            JobAnalysisRequestDto request
    );

    JobAnalysisResponseDto getAnalysis(
            Long applicationId
    );
}