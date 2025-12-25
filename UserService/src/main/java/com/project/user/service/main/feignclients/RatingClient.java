package com.project.user.service.main.feignclients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.user.service.main.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingClient {

	@GetMapping("/ratings/userId/{userId}")
	public List<Rating> getRatingsByUserId(@PathVariable String userId);
	
	
	// These below examples are just for knowledge and they have no  use case in our current project
	
//	@PostMapping("/ratings")
//	public ResponseEntity<Rating> postRating(@RequestBody Rating rating);
//	
//	@PutMapping("/ratings/ratingId/{ratingId}") // we have not created this API yet in put Rating Service
//	public Rating updateRating(@RequestBody Rating rating, @PathVariable String ratingId); 
//	
//	@DeleteMapping("/ratings/ratingId/{ratingId}") // we have not created this API yet in put Rating Service
//	public void deleteRating(@PathVariable String ratingId);
}
