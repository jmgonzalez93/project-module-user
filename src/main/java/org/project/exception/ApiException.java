package org.project.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiException extends RuntimeException {

    private final LocalDateTime timestamp;
    private final String message;


    public ApiException(String message) {
        super(message);
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
