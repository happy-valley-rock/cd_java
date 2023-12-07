package com.pos.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class ErrorResponse {
    private String traceId;
    private OffsetDateTime timestamp;
    private int status;
    private String error;
    private String path;

    public ErrorResponse() {
        this.traceId = UUID.randomUUID().toString();
        this.timestamp = OffsetDateTime.now();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        this.path = "/";
    }

    public ErrorResponse(HttpStatus status, String error, String path) {
        if (error == null) error = status.getReasonPhrase();
        this.traceId = UUID.randomUUID().toString();
        this.timestamp = OffsetDateTime.now();
        this.status = status.value();
        this.error = error;
        this.path = path;
    }

    public ErrorResponse(HttpStatus status, String error) {
        this.traceId = UUID.randomUUID().toString();
        this.timestamp = OffsetDateTime.now();
        this.status = status.value();
        this.error = error;
        this.path = "/";
    }
}
