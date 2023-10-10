package com.test.restapi.RestApi.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{
	// if we want to handle nullpointer exception only we have to replace the exception.class to nullpointerException.class
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest req)
	{
		System.out.println("we are in the custom Exception class");
		return new ResponseEntity<Object> (ex , new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handleNullPointer_SpecialException_Handler(Exception ex, WebRequest req)
	{
		System.out.println("we are in the custom NullPointerException class");
		return new ResponseEntity<Object> (ex , new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
