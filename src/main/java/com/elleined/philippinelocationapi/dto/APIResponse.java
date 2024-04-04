package com.elleined.philippinelocationapi.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class APIResponse {

    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timeStamp;

    public APIResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }
}
