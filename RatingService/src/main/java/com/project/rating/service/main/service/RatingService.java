package com.project.rating.service.main.service;

import java.util.List;

import com.project.rating.service.main.entity.Rating;

public interface RatingService {
	// create rating
	public Rating saveRating(Rating rating);
	
	// get all ratings
	public List<Rating> getAllRatings();

	// get all ratings by UserId
	public List<Rating> getRatingsByUserId(String userId);
	
	// get all ratings corresponding to a hotel
	public List<Rating> getRatingsByHotelId(String hotelId);
}
