package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class PlayerServiceException extends RuntimeException {
private String errorMessage;
private HttpStatus httpStatus;

       public PlayerServiceException(String errorMessage,HttpStatus httpStatus) {
    	   this.errorMessage = errorMessage;
    	   this.httpStatus = httpStatus;
       }

     public String getErrMessage() {
	     return errorMessage;
      }
     
     public HttpStatus getHttpStatus() {
    	 return httpStatus;
     }
}
