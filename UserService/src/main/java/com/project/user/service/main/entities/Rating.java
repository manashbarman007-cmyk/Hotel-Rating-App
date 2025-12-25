package com.project.user.service.main.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// We will get this rating from another service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	
	private String ratingId;
	
	private String userId;
	
	private String hotelId;
	
	private int rating; // out of 5 start
	
	private String feedback;
	
	private Hotel hotel;

}
