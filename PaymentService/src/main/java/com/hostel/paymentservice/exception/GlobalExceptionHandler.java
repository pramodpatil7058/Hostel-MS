package com.hostel.paymentservice.exception;

import com.hostel.paymentservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<ApiResponse> exceptionHandler(ResourceNotFoundException e){
        ApiResponse response = ApiResponse.builder().message(e.getMessage()).httpStatus(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
