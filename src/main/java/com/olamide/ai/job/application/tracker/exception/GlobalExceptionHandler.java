package com.olamide.ai.job.application.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleApplicationNotFound(ApplicationNotFoundException exception) {
        return exception.getMessage();
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException exception) {
        return exception.getMessage();
    }
    @ExceptionHandler(UnathorizedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUnathorizedException(UnathorizedException exception) {
        return exception.getMessage();
    }
}