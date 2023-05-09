package com.salon.salon.exceptions;

import org.springframework.http.HttpStatus;

public class CustomNotFoundExсeption extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public CustomNotFoundExсeption(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
    
}
