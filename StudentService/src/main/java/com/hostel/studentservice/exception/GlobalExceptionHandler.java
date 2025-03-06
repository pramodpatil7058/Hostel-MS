package com.hostel.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hostel.studentservice.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
		
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceExceptionHandler(ResourceNotFoundException e){
		String message = e.getMessage();
		ApiResponse response = ApiResponse
				.builder()
				.message(message)
				.success(false)
				.httpStatus(HttpStatus.NOT_FOUND)
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(value=PaymentException.class)
	public ResponseEntity<ApiResponse> paymentException(PaymentException e){
		String message = e.getMessage();
		ApiResponse response = ApiResponse
				.builder()
				.message(message)
				.success(false)
				.httpStatus(HttpStatus.NOT_FOUND)
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> methodArgumentException(MethodArgumentNotValidException e){
		String message = e.getMessage();
		ApiResponse response = ApiResponse
				.builder()
				.message(message)
				.success(false)
				.httpStatus(HttpStatus.NOT_FOUND)
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
}
