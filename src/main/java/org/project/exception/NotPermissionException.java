package org.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotPermissionException extends ApiException {

    public NotPermissionException(String message) {
        super(message);
    }
}
