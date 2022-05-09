package com.etaskify.etaskifyapp.dto;

import com.etaskify.etaskifyapp.enums.AppMessage;
import lombok.Data;

@Data
public class ResponseDto {
    private int code;
    private String message;


    public ResponseDto(AppMessage appMessage) {
        this.code = appMessage.getCode();
        this.message = appMessage.getMessage();
    }
}
