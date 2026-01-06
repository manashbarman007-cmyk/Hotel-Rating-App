package com.project.rating.service.main.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.project.rating.service.main.entity.Rating;
import com.project.rating.service.main.exception.HotelNotFoundException;
import com.project.rating.service.main.exception.UserNotFoundException;
import com.project.rating.service.main.repository.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepo ratingRepo;

	@Autowired
	private MongoTemplate mongoTemplate; //Spring Boot configures MongoTemplate automatically

	@Override
	public Rating saveRating(Rating rating) {
		Rating savedRating = ratingRepo.save(rating);
		return savedRating;
	}

	@Override
	public List<Rating> getAllRatings() {
		List<Rating> allRatings = ratingRepo.findAll();
		return allRatings;
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) {


		Criteria criteria = Criteria.where("userId").is(userId);
		Query query = new Query(criteria);
		List<Rating> ratings = mongoTemplate.find(query, Rating.class);	
		if (!ratings.isEmpty()) {
			return ratings;
		} else {
			throw new UserNotFoundException("User not found with id : " + userId);
		}
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		Criteria criteria = Criteria.where("hotelId").is(hotelId);
		Query query = new Query(criteria);
		List<Rating> ratings = mongoTemplate.find(query, Rating.class);

		if (!ratings.isEmpty()) {
			return ratings;
		} else {
			throw new HotelNotFoundException("Hotel not found with id : " + hotelId);
		}
	}

}
