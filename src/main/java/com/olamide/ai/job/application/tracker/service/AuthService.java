package com.olamide.ai.job.application.tracker.service;

import com.olamide.ai.job.application.tracker.dto.LoginRequestDto;

public interface AuthService {

    String login(LoginRequestDto request);
}