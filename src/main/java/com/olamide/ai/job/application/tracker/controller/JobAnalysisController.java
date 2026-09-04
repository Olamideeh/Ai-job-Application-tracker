package com.olamide.ai.job.application.tracker.controller;

import com.olamide.ai.job.application.tracker.dto.JobAnalysisRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobAnalysisResponseDto;
import com.olamide.ai.job.application.tracker.response.ApiResponse;
import com.olamide.ai.job.application.tracker.service.JobAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Tag(
        name = "AI Job Analysis",
        description = "Analyze job descriptions for job applications"
)
public class JobAnalysisController {

    private final JobAnalysisService jobAnalysisService;

    @PostMapping("/{applicationId}/analysis")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create job analysis"
    )
    public ApiResponse<JobAnalysisResponseDto> createAnalysis(
            @PathVariable Long applicationId,
            @Valid @RequestBody JobAnalysisRequestDto request
    ) {

        JobAnalysisResponseDto analysis =
                jobAnalysisService.createAnalysis(
                        applicationId,
                        request
                );

        return new ApiResponse<>(
                true,
                "Job analysis created successfully",
                analysis
        );
    }

    @GetMapping("/{applicationId}/analysis")
    @Operation(
            summary = "Get job analysis"
    )
    public ApiResponse<JobAnalysisResponseDto> getAnalysis(
            @PathVariable Long applicationId
    ) {

        JobAnalysisResponseDto analysis =
                jobAnalysisService.getAnalysis(applicationId);

        return new ApiResponse<>(
                true,
                "Job analysis retrieved successfully",
                analysis
        );
    }
}