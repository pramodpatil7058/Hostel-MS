package com.hostel.paymentservice.exception;

import com.hostel.paymentservice.payload.ApiResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> exceptionHandler(ResourceNotFoundException e){
        ApiResponse response = ApiResponse.builder().message(e.getMessage()).httpStatus(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
	
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> alreadyExist(ResourceAlreadyExistsException e){
    	 ApiResponse response = ApiResponse.builder().message(e.getMessage()).httpStatus(HttpStatus.CONFLICT).success(true).build();
         return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> constraintExceptionHandler(ConstraintViolationException e){
        ApiResponse response = ApiResponse.builder().message("Invalid Transaction : Transaction Id already exist.").httpStatus(HttpStatus.ALREADY_REPORTED).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
