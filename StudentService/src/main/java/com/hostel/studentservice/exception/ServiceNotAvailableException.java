package com.hostel.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceNotAvailableException extends RuntimeException{
    public ServiceNotAvailableException(){
        super("This service is not available at this moment.");
    }
}
