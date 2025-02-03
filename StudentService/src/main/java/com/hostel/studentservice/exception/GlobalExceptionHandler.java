package com.hostel.studentservice.exception;

import com.hostel.studentservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    //? Handles No Resource Found Exception
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> exceptionHandler(NoResourceFoundException e){
        String message= e.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //? Checks if validation throws any exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> validationExceptionHandler(MethodArgumentNotValidException e){
        String message = e.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).httpStatus(HttpStatus.BAD_REQUEST).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
