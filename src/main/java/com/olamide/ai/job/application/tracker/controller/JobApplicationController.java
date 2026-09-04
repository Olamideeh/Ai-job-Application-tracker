package com.olamide.ai.job.application.tracker.controller;

import com.olamide.ai.job.application.tracker.dto.JobApplicationRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobApplicationResponseDto;
import com.olamide.ai.job.application.tracker.enums.ApplicationStatus;
import com.olamide.ai.job.application.tracker.response.ApiResponse;
import com.olamide.ai.job.application.tracker.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<JobApplicationResponseDto> createApplication(
            @Valid @RequestBody JobApplicationRequestDto request
    ) {

        JobApplicationResponseDto application =
                jobApplicationService.createApplication(request);

        return new ApiResponse<>(
                true,
                "Application created successfully",
                application
        );
    }

    @GetMapping
    public ApiResponse<Page<JobApplicationResponseDto>> getAllApplications(
            Pageable pageable
    ) {

        Page<JobApplicationResponseDto> applications =
                jobApplicationService.getAll(pageable);

        return new ApiResponse<>(
                true,
                "Applications retrieved successfully",
                applications
        );
    }

    @GetMapping("/my-applications")
    public ApiResponse<List<JobApplicationResponseDto>> findByEmail() {

        List<JobApplicationResponseDto> applications =
                jobApplicationService.findByEmail();

        return new ApiResponse<>(
                true,
                "Your applications retrieved successfully",
                applications
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<JobApplicationResponseDto> findApplicationById(
            @PathVariable Long id
    ) {

        JobApplicationResponseDto application =
                jobApplicationService.getById(id);

        return new ApiResponse<>(
                true,
                "Application retrieved successfully",
                application
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<JobApplicationResponseDto> updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationRequestDto request
    ) {

        JobApplicationResponseDto application =
                jobApplicationService.update(id, request);

        return new ApiResponse<>(
                true,
                "Application updated successfully",
                application
        );
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<JobApplicationResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status
    ) {

        JobApplicationResponseDto application =
                jobApplicationService.updateStatus(id, status);

        return new ApiResponse<>(
                true,
                "Application status updated successfully",
                application
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteApplication(
            @PathVariable Long id
    ) {

        jobApplicationService.delete(id);

        return new ApiResponse<>(
                true,
                "Application deleted successfully",
                null
        );
    }

    @GetMapping("/filter")
    public ApiResponse<Page<JobApplicationResponseDto>> findByStatus(
            @RequestParam ApplicationStatus status,
            Pageable pageable
    ) {

        Page<JobApplicationResponseDto> applications =
                jobApplicationService.findByStatus(status, pageable);

        return new ApiResponse<>(
                true,
                "Applications filtered successfully",
                applications
        );
    }

    @GetMapping("/search")
    public ApiResponse<Page<JobApplicationResponseDto>> search(
            @RequestParam String keyword,
            Pageable pageable
    ) {

        Page<JobApplicationResponseDto> applications =
                jobApplicationService.search(keyword, pageable);

        return new ApiResponse<>(
                true,
                "Applications searched successfully",
                applications
        );
    }
}