package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class PlayerServiceException extends RuntimeException {
private String errorMessage;
private HttpStatus httpStatus;

 
     public String getMessage() {
	     return errorMessage;
      }
     
     public HttpStatus getHttpStatus() {
    	 return httpStatus;
     }
     
     public PlayerServiceException(String errorMessage,HttpStatus httpStatus) {
  	   this.errorMessage = errorMessage;
  	   this.httpStatus = httpStatus;
     }
}
