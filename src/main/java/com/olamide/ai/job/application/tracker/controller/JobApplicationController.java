package com.olamide.ai.job.application.tracker.controller;

import com.olamide.ai.job.application.tracker.dto.JobApplicationRequestDto;
import com.olamide.ai.job.application.tracker.dto.JobApplicationResponseDto;
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
}