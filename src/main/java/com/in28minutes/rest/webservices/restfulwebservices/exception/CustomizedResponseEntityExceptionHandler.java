package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
 
/* It's a specialization of component for classes that declare @ExceptionHandlers methods to be shared
 * across multiple @Controller classes.*/
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
 
	/* This is for 404 RESOURCE NOT FOUND */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

		ErrorDetails errorDetails = 
				new ErrorDetails(
					LocalDateTime.now(), 
					ex.getMessage(), 
					request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
 
	/* This is for 400 BAD REQUEST */
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {

		ErrorDetails errorDetails = 
				new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		/* You can customize the error message that you want to return. */
		ErrorDetails errorDetails = 
				new ErrorDetails(
					LocalDateTime.now(), 
					"Total Errors: " + ex.getErrorCount() + ". First Error: " + ex.getFieldError().getDefaultMessage(),
					request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}