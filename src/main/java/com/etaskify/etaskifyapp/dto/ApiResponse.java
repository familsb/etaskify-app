package com.etaskify.etaskifyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int status;
    private ApiError error;
    private T data;

    public static final int API_RESPONSE_SUCCESS = 1;
    public static final int API_RESPONSE_ERROR = 0;

    public static <T> ApiResponse<T> withSuccess(T data) {
        return new ApiResponse<>(API_RESPONSE_SUCCESS, null, data);
    }

    public static ApiResponse withError(ApiError error) {
        return new ApiResponse<>(API_RESPONSE_ERROR, error, null);
    }

}
