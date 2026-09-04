package com.olamide.ai.job.application.tracker.dto;

import com.olamide.ai.job.application.tracker.entity.JobAnalysis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAnalysisResponseDto {

    private Long id;

    private Long applicationId;

    private String jobDescription;

    private String summary;

    private String requiredSkills;

    private String missingSkills;

    private String recommendations;

    private String seniority;

    private Integer matchScore;

    public static JobAnalysisResponseDto fromEntity(
            JobAnalysis analysis
    ) {
        return new JobAnalysisResponseDto(
                analysis.getId(),
                analysis.getJobApplication().getId(),
                analysis.getJobDescription(),
                analysis.getSummary(),
                analysis.getRequiredSkills(),
                analysis.getMissingSkills(),
                analysis.getRecommendations(),
                analysis.getSeniority(),
                analysis.getMatchScore()
        );
    }
}