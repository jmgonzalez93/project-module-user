package org.project.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ResponseEntityDTO {

    private final LocalDateTime timestamp;
    private final String message;
    private Object data;
}
