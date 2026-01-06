package com.project.user.service.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.user.service.main.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> handleUserNotFound(UserNotFoundException ex) {
		
		ApiResponse apiResponse = ApiResponse.builder()
				              .message(ex.getMessage())
				              .success(true)
				              .httpStatus(HttpStatus.NOT_FOUND)
				              .build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
