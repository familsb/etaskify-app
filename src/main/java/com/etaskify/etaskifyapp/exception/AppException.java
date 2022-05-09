package com.etaskify.etaskifyapp.exception;

import com.etaskify.etaskifyapp.enums.AppMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppException extends RuntimeException {
    private final int code;
    private final String message;

    public AppException(AppMessage portalError) {
        this.code = portalError.getCode();
        this.message = portalError.getMessage();

    }
}
