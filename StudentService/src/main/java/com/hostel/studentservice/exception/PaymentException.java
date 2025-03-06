package com.hostel.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.ALREADY_REPORTED)
public class PaymentException extends RuntimeException {
	public PaymentException(String message) {
		super(message);
	}
}
