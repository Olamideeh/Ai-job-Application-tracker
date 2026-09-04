package com.olamide.ai.job.application.tracker.exception;

import com.olamide.ai.job.application.tracker.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> handleApplicationNotFound(
            ApplicationNotFoundException exception
    ) {

        return new ApiResponse<>(
                false,
                exception.getMessage(),
                null
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> handleUserNotFound(
            UserNotFoundException exception
    ) {

        return new ApiResponse<>(
                false,
                exception.getMessage(),
                null
        );
    }
}