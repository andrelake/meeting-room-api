package br.com.andrelake.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException e, WebRequest req) {
		
		Problem problem = new Problem(new Date(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(problem, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception e, WebRequest req) {
		
		Problem problem = new Problem(new Date(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
