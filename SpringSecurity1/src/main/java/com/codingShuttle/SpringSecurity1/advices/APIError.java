package com.codingShuttle.SpringSecurity1.advices;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class APIError {

    private LocalDateTime timestamp;
    private String error;
    private HttpStatus status;


    public APIError(String error, HttpStatus status){
        this.timestamp = LocalDateTime.now();
        this.error = error;
        this.status = status;
    }
}
