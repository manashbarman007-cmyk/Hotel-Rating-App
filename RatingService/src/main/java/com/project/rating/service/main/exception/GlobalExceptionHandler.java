package com.project.rating.service.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.project.rating.service.main.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<ApiResponse> handleHotelNotFound(HotelNotFoundException ex) {
		ApiResponse apiResponse = ApiResponse.builder()
				                  .message(ex.getMessage())
				                  .success(true)
				                  .httpStatus(HttpStatus.NOT_FOUND)
				                  .build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
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
