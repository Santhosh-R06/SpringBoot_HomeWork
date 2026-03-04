package com.module2.DepartmentManagement.advices;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class APIResponse<T> {

    private T data;
    private APIError apiError;
    @JsonFormat(pattern = "dd-MM-yyyy-hh:mm:ss")
    private LocalDateTime timestamp;

    public APIResponse() {
        this.timestamp = LocalDateTime.now();
    }
    public APIResponse(T data) {
        this();
        this.data = data;
    }
    public APIResponse(APIError apiError) {
        this();
        this.apiError = apiError;
    }
}
