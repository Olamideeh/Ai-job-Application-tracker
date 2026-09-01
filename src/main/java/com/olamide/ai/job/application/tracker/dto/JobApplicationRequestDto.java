package com.olamide.ai.job.application.tracker.dto;

import com.olamide.ai.job.application.tracker.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationRequestDto {

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    private String companyName;

    @NotBlank(message = "Job title is required")
    @Size(max = 100, message = "Job title cannot exceed 100 characters")
    private String jobTitle;

    @Size(max = 100, message = "Location cannot exceed 100 characters")
    private String location;

    private String jobUrl;

    @NotNull(message = "Application status is required")
    private ApplicationStatus status;

    @NotNull(message = "Application date is required")
    private LocalDate applicationDate;

    @Size(max = 2000, message = "Notes cannot exceed 2000 characters")
    private String notes;
}