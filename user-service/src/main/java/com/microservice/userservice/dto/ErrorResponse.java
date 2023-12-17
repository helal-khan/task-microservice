package com.microservice.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorResponse {
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private HttpStatus status;

    private String message;

    private String reason;

    @Builder.Default
    private Map<String, Object> errors = new HashMap<>();

    public int getStatus() {
        return status.value();
    }
}
