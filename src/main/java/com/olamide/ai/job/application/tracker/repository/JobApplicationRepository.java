package com.olamide.ai.job.application.tracker.repository;

import com.olamide.ai.job.application.tracker.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserEmail(String email);
}