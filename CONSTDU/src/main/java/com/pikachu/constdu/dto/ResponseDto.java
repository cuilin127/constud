package com.pikachu.constdu.dto;

public class ResponseDto {
    public String message;
    public Object data;

    public ResponseDto(String message, Object data){
        this.data = data;
        this.message = message;
    }
    public ResponseDto(String message){
        this.message = message;
        this.data = null;
    }
}

