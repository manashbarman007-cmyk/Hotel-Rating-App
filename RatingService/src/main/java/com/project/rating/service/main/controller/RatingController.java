package com.project.rating.service.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rating.service.main.entity.Rating;
import com.project.rating.service.main.service.RatingServiceImpl;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingServiceImpl impl;
	
	@PostMapping 
	public ResponseEntity<Rating> insertRating(@RequestBody Rating rating) {
		Rating saveRating = impl.saveRating(rating);
		return ResponseEntity.status(HttpStatus.OK).body(saveRating);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_internal')") // the custom scope that we created
                                                    // Only the UserService has the "internal" scope (even the ApiGateway
                                                    // does not have it). Thus, only the UserService can access this API
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> ratings = impl.getAllRatings();
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}
	
	@GetMapping("/userId/{userId}")
	@PreAuthorize("hasAuthority('SCOPE_internal')") // the custom scope that we created
                                                    // Only the UserService has the "internal" scope (even the ApiGateway
                                                    // does not have it). Thus, only the UserService can access this API
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
		List<Rating> ratings = impl.getRatingsByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}
	
	@GetMapping("/hotelId/{hotelId}")
	@PreAuthorize("hasAuthority('SCOPE_internal')") // the custom scope that we created
                                                    // Only the UserService has the "internal" scope (even the ApiGateway
                                                    // does not have it). Thus, only the UserService can access this API
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
		List<Rating> ratings = impl.getRatingsByHotelId(hotelId);
		return ResponseEntity.status(HttpStatus.OK).body(ratings);
	}

}
