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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Tag(
        name = "Job Applications",
        description = "Endpoints for managing job applications"
)
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    @Operation(
            summary = "Create a job application",
            description = "Creates a new job application for the authenticated user"
    )
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

    @Operation(
            summary = "Get all job applications",
            description = "Returns a paginated list of job applications"
    )
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

    @Operation(
            summary = "Get my applications",
            description = "Returns applications belonging to the authenticated user"
    )
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

    @Operation(
            summary = "Get application by ID",
            description = "Returns a single job application using its ID"
    )
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

    @Operation(
            summary = "Update a job application",
            description = "Updates an existing job application"
    )
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

    @Operation(
            summary = "Update application status",
            description = "Changes the status of an existing job application"
    )
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
    @Operation(
            summary = "Delete a job application",
            description = "Deletes an existing job application"
    )
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

    @Operation(
            summary = "Filter applications by status",
            description = "Returns applications matching the specified status"
    )
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

    @Operation(
            summary = "Search job applications",
            description = "Searches applications by company name or job title"
    )
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