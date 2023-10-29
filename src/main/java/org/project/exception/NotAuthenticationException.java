package org.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthenticationException extends ApiException {

    public NotAuthenticationException(String message) {
        super(message);
    }
}
