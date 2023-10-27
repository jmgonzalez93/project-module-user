package org.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserException extends ApiException {

    public UserException(String message) {
        super(message);
    }
}
