package com.stacksimplifly.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValidException

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		CustomErrorDetails cd = new CustomErrorDetails(new Date(), "From MethodArgumentNotValidException in GEH",
				ex.getMessage());

		
		return new ResponseEntity<Object>(cd, status.BAD_REQUEST);
	}


	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex,
			WebRequest request) {
		
		CustomErrorDetails cd = new CustomErrorDetails(new Date(),
				ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<Object>(cd, HttpStatus.NOT_FOUND);
	}

	

}
