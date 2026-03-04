package com.module2.DepartmentManagement.advices;


import com.module2.DepartmentManagement.expections.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex){
        APIError apiError = APIError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<?>> handleInternalErrorException(Exception ex){
        APIError apiError = APIError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors()  //.getAllErrors()   //.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        APIError apiError = APIError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation failed.")
                .subErrors(fieldErrors)
                .build();

        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<APIResponse<?>> buildErrorResponseEntity(APIError apiError){
        return new ResponseEntity<>(new APIResponse<>(apiError), apiError.getStatus());
    }

}
