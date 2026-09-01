package com.olamide.ai.job.application.tracker.dto;

import com.olamide.ai.job.application.tracker.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationResponseDto {

    private Long id;

    private String companyName;

    private String jobTitle;

    private String location;

    private String jobUrl;

    private ApplicationStatus status;

    private LocalDate applicationDate;

    private String notes;
}