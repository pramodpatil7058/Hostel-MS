package com.hostel.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}
