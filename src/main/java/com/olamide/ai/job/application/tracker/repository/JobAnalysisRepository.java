package com.olamide.ai.job.application.tracker.repository;

import com.olamide.ai.job.application.tracker.entity.JobAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobAnalysisRepository
        extends JpaRepository<JobAnalysis, Long> {

    Optional<JobAnalysis> findByJobApplicationId(Long applicationId);
}