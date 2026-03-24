package com.codingShuttle.convertCurrency.advices;

import com.codingShuttle.convertCurrency.exceptions.CurrencyInvalidException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CurrencyInvalidException.class)
    public ResponseEntity<APIResponse<Void>> handleCurrencyInvalidException(CurrencyInvalidException e) {
        log.error("handleCurrencyInvalidException 4xx API Error: {}",e.getMessage());
        APIError error = new APIError(e.getMessage(), e.getStatus(), e.getErrors());
        return ResponseEntity.status(e.getStatus()).body(new APIResponse<>(error));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Void>> handleUnitsInvalidException(ConstraintViolationException e) {
        log.error("handleUnitsInvalidException Validation Error: {}",e.getMessage());
        Map<String, List<String>> errors = new HashMap<>();
        e.getConstraintViolations().forEach(cv -> {
            String fieldName = cv.getPropertyPath().toString();
            String message = cv.getMessage();
            errors.computeIfAbsent(fieldName,k-> new ArrayList<>()).add(message);
        });
        APIError error = new APIError("Validation Error", HttpStatus.BAD_REQUEST, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(error));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException Type mismatch Error: {}",e.getMessage());

        Map<String, List<String>> errors = new HashMap<>();
        errors.put(e.getName() , List.of("Invalid Value '" +  e.getValue() + "' - must be a valid " + e.getRequiredType().getSimpleName()));
        APIError error = new APIError("Type mismatch Error", HttpStatus.BAD_REQUEST, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>(error));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIResponse<Void>> handleRuntimeException(RuntimeException e) {
        log.error("handleRuntimeException Internal Server Error: {}",e.getMessage());
        APIError error = new APIError("Internal Server Error",  HttpStatus.INTERNAL_SERVER_ERROR, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>(error));
    }


}
