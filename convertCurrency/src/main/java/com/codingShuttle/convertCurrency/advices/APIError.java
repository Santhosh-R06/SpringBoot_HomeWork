package com.codingShuttle.convertCurrency.advices;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class APIError {

    private LocalDateTime timestamp;

    private String error;

    private HttpStatus statusCode;

    private Map<String, List<String>> errors;

    public APIError(String error, HttpStatus statusCode, Map<String, List<String>> errors) {
        this.timestamp = LocalDateTime.now();
        this.error = error;
        this.statusCode = statusCode;
        this.errors = errors;
    }

}
