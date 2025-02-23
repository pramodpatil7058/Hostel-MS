package com.hostel.wardenservice.exception;

import com.hostel.wardenservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> exceptionHandler(ResourceNotFoundException e) {
		ApiResponse response = ApiResponse.builder().message(e.getMessage()).success(true)
				.httpStatus(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IncompleteTransactionException.class)
	public ResponseEntity<ApiResponse> exceptionHandler(IncompleteTransactionException e) {
		ApiResponse response = ApiResponse.builder().message(e.getMessage()).success(true)
				.httpStatus(HttpStatus.NOT_ACCEPTABLE).build();
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}
}
