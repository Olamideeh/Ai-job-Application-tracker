package com.olamide.ai.job.application.tracker.exception;

public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException(String message) {
        super(message);
    }
}