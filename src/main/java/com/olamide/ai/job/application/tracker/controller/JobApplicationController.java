package com.olamide.ai.job.application.tracker.controller;
import com.olamide.ai.job.application.tracker.dto.JobApplicationRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobApplicationResponseDto;
import com.olamide.ai.job.application.tracker.enums.ApplicationStatus;
import com.olamide.ai.job.application.tracker.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public JobApplicationResponseDto createApplication(
            @Valid @RequestBody JobApplicationRequestDto request
    ) {
        return jobApplicationService.createApplication(request);
    }

    @GetMapping
    public List<JobApplicationResponseDto> getAllApplications() {
        return jobApplicationService.getAll();
    }

    @GetMapping("/my-applications")
    public List<JobApplicationResponseDto> findByEmail() {
        return jobApplicationService.findByEmail();
    }

    @GetMapping("/{id}")
    public JobApplicationResponseDto findApplicationById(
            @PathVariable Long id
    ) {
        return jobApplicationService.getById(id);
    }

    @PutMapping("/{id}")
    public JobApplicationResponseDto updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationRequestDto request
    ) {
        return jobApplicationService.update(id, request);
    }

    @PatchMapping("/{id}/status")
    public JobApplicationResponseDto updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status
    ) {
        return jobApplicationService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(
            @PathVariable Long id
    ) {
        jobApplicationService.delete(id);
    }
}
