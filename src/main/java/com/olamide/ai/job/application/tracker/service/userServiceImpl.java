package com.olamide.ai.job.application.tracker.service;

import com.olamide.ai.job.application.tracker.dto.LoginRequestDto;
import com.olamide.ai.job.application.tracker.dto.LoginResponseDto;
import com.olamide.ai.job.application.tracker.dto.UserRequestDto;
import com.olamide.ai.job.application.tracker.dto.UserResponseDto;
import com.olamide.ai.job.application.tracker.entity.User;
import com.olamide.ai.job.application.tracker.enums.Role;
import com.olamide.ai.job.application.tracker.repository.UserRepository;
import com.olamide.ai.job.application.tracker.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public UserResponseDto register(UserRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setFullname(request.getFullName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getFullname(),
                savedUser.getEmail(),
                savedUser.getPassword()
        );
    }


    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token =
                jwtService.generateToken(userDetails);

        return new LoginResponseDto(token);
    }
}