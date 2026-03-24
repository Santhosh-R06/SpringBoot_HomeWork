package com.codingShuttle.convertCurrency.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class CurrencyInvalidException extends RuntimeException {

    private final HttpStatus statusCode;
    private final Map<String, List<String>> errors;

    public CurrencyInvalidException(String message, HttpStatus statusCode, Map<String, List<String>> errors) {
        super(message);
        this.statusCode = statusCode;
        this.errors = errors;
    }


    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public Map<String, List<String>> getErrors() {
        return this.errors;
    }
}
