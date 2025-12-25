package com.project.rating.service.main.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "micro_ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	
	@Id
	private String ratingId;

	private String userId;

	private String hotelId;

	private int rating; // out of 5 start

	private String feedback;

}
