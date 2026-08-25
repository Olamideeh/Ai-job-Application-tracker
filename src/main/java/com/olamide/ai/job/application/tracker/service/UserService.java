package com.olamide.ai.job.application.tracker.service;

import com.olamide.ai.job.application.tracker.dto.LoginRequestDto;
import com.olamide.ai.job.application.tracker.dto.LoginResponseDto;
import com.olamide.ai.job.application.tracker.dto.UserRequestDto;
import com.olamide.ai.job.application.tracker.dto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserRequestDto request);

    LoginResponseDto login(LoginRequestDto request);
}