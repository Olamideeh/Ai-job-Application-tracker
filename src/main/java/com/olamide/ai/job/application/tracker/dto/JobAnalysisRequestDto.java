package com.olamide.ai.job.application.tracker.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAnalysisRequestDto {

    @NotBlank(message = "Job description cannot be blank")
    private String jobDescription;

    private String summary;

    private String requiredSkills;

    private String missingSkills;

    private String recommendations;

    private String seniority;

    @Min(value = 0, message = "Match score cannot be below 0")
    @Max(value = 100, message = "Match score cannot exceed 100")
    private Integer matchScore;
}