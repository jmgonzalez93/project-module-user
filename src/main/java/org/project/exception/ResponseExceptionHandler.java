package org.project.exception;

import lombok.extern.log4j.Log4j2;
import org.project.service.dto.ResponseEntityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseEntityDTO> doApiException(ApiException apiException) {
        log.error("[ERROR] error_message: {}", apiException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ResponseEntityDTO
                        .builder()
                        .message(apiException.getMessage())
                        .timestamp(apiException.getTimestamp())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseEntityDTO> doApiException(NotFoundException notFoundException) {
        log.error("[ERROR] error_message: {}", notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseEntityDTO
                        .builder()
                        .message(notFoundException.getMessage())
                        .timestamp(notFoundException.getTimestamp())
                        .build());
    }

    @ExceptionHandler(NotAuthenticationException.class)
    public ResponseEntity<ResponseEntityDTO> doApiException(NotAuthenticationException notAuthenticationException) {
        log.error("[ERROR] error_message: {}", notAuthenticationException.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseEntityDTO
                        .builder()
                        .message(notAuthenticationException.getMessage())
                        .timestamp(notAuthenticationException.getTimestamp())
                        .build());
    }

    @ExceptionHandler(NotPermissionException.class)
    public ResponseEntity<ResponseEntityDTO> doApiException(NotPermissionException notPermissionException) {
        log.error("[ERROR] error_message: {}", notPermissionException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseEntityDTO
                        .builder()
                        .message(notPermissionException.getMessage())
                        .timestamp(notPermissionException.getTimestamp())
                        .build());
    }
}
