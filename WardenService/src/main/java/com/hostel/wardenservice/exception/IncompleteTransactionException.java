package com.hostel.wardenservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class IncompleteTransactionException extends RuntimeException{
	public IncompleteTransactionException(String message) {
		super(message);
	}
}
