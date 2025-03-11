package com.service.perkpoint.base;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleUsernameNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {
		String errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.joining(","));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
		System.err.println(ex.getReason());
		PpException errorDetails = new PpException(new Date(), ex.getReason(), request.getDescription(false));
		HttpStatusCode statusCode = ex.getStatusCode();
		return new ResponseEntity<>(errorDetails, statusCode);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleUsernameNotFoundException(BadCredentialsException ex, WebRequest request) {
		PpException errorDetails = new PpException(new Date(), "Email or Password is Wrong",
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
		System.err.println(ex.getMessage());
		PpException errorDetails = new PpException(new Date(), "Something Went Wrong", request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
