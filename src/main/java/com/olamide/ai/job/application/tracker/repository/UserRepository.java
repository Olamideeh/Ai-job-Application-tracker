package com.olamide.ai.job.application.tracker.repository;


import com.olamide.ai.job.application.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
        Optional<User> findByUsername(String username);
}
