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
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> exceptionHandler(NoResourceFoundException e){
        String message= e.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> validationExceptionHandler(MethodArgumentNotValidException e){
        String message = e.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).httpStatus(HttpStatus.BAD_REQUEST).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> customExceptionHandler(CustomException e){
        String message = e.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).httpStatus(HttpStatus.UNPROCESSABLE_ENTITY).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
