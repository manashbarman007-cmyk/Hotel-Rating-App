package com.project.rating.service.main.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
	
	private String message;
	private boolean success;
	private HttpStatus httpStatus;

}
