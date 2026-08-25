package com.olamide.ai.job.application.tracker.controller;

import com.olamide.ai.job.application.tracker.dto.LoginRequestDto;
import com.olamide.ai.job.application.tracker.dto.LoginResponseDto;
import com.olamide.ai.job.application.tracker.dto.UserRequestDto;
import com.olamide.ai.job.application.tracker.dto.UserResponseDto;
import com.olamide.ai.job.application.tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public UserResponseDto register(
            @Valid @RequestBody UserRequestDto request
    ) {

        return userService.register(request);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request
    ) {

        LoginResponseDto response =
                userService.login(request);

        return ResponseEntity.ok(response);
    }
}