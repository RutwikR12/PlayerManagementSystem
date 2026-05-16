package com.example.demo.globalExceptionHandller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.PlayerServiceException;

@ControllerAdvice
public class GlobalExceptionHandller {

	@ExceptionHandler(PlayerServiceException.class)
	public ResponseEntity<String> handlePlayerServiceException(PlayerServiceException pe) {
		System.out.println("Player Service Exception handlled by Global Exception Handller");
		return new ResponseEntity<String>(pe.getMessage(),pe.getHttpStatus());
	}
	 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		System.out.println("Exception handlled by Global Exception Handller");
		return new ResponseEntity<String>("Something went wrong",HttpStatus.BAD_REQUEST);
	}
	
}
