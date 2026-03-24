package com.codingShuttle.convertCurrency.advices;


import lombok.Data;

@Data
public class APIResponse<T> {

    private T data;

    private APIError error;

    public APIResponse() {}
    public APIResponse(T data) {
        this();
        this.data = data;
    }
    public APIResponse(APIError error) {
        this();
        this.error = error;
    }
}
