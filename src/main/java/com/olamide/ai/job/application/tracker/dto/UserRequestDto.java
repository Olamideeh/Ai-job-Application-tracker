package com.olamide.ai.job.application.tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "full name is required")
    private String fullName;
    @NotBlank(message = "Email is required")
    @Email(message = ("enter a valid email"))
    private String email;
    @NotBlank(message = "password is required")
    @Size(message = "password must be at least 8 character")
    private String password;
    @NotBlank(message = "Username is required")
    private String userName;

}
