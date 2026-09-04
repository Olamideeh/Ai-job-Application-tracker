package com.olamide.ai.job.application.tracker.repository;

import com.olamide.ai.job.application.tracker.entity.JobApplication;
import com.olamide.ai.job.application.tracker.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    Page<JobApplication> findAll(Pageable pageable);
    List<JobApplication> findByUserEmail(String email);
    Page<JobApplication> findByStatus(ApplicationStatus status, Pageable pageable);
    Page<JobApplication> findByCompanyNameContainingIgnoreCaseOrJobTitleContainingIgnoreCase(
            String companyName,
            String jobTitle,
            Pageable pageable
    );

}