package com.project.user.service.main.services;


import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.user.service.main.entities.Hotel;
import com.project.user.service.main.entities.Rating;
import com.project.user.service.main.entities.User;
import com.project.user.service.main.exception.UserNotFoundException;
import com.project.user.service.main.feignclients.HotelClient;
import com.project.user.service.main.feignclients.RatingClient;
import com.project.user.service.main.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RatingClient ratingClient;
	
	@Autowired
	private HotelClient hotelClient;

	@Override
	public User insertUser(User user) {
		user.setUserId(UUID.randomUUID().toString()); // we provide a random UUID;
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	@CircuitBreaker(name = "ratingHotelBreaker1", fallbackMethod = "fallback1")
	@Retry(name = "ratingHotelBreaker1")
	public List<User> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		
		for (User user : allUsers) {
			String user_id = user.getUserId();
			List<Rating> ratingsList = ratingClient.getRatingsByUserId(user_id);
			
			for(Rating rating : ratingsList) {
				String hotelId = rating.getHotelId();
				
				Hotel hotel = hotelClient.getHotelByHotelId(hotelId);
				
				rating.setHotel(hotel);
			}
			user.setRatings(ratingsList);
			
		}
		return allUsers;
	}
	
	public List<User> fallback1(Throwable t) {
		log.info("Service is down due to :  " + t.getMessage());
		User user = User.builder() // dummy user to be returned
		           .name("dummy")
		           .email("xxx@xxx.com")
		           .userId("123")
		           .build();
		return List.of(user);
	}

	@Override
	@CircuitBreaker(name = "ratingHotelBreaker2", fallbackMethod = "fallback2")
	@Retry(name = "ratingHotelBreaker2")
	public User getUserById(String user_id) {
		User user = userRepository.findById(user_id).orElseThrow(() ->
		                     new UserNotFoundException("User not found with id : " + user_id));
		List<Rating> ratingsList = ratingClient.getRatingsByUserId(user_id);		
		for(Rating rating : ratingsList) {
			String hotelId = rating.getHotelId();
			
			// we will call http://localhost:8082/hotels/id/{hotel_id} (rating service is running in 8082 port)

			Hotel hotel = hotelClient.getHotelByHotelId(hotelId);
			
			rating.setHotel(hotel);
		}
		user.setRatings(ratingsList);
		
		return user;
	}
	
	public User fallback2(String user_id, Throwable t) {
		log.info("Service is down due to :  " + t.getMessage());
		User user = User.builder() // dummy user to be returned
		           .name("dummy")
		           .email("xxx@xxx.com")
		           .userId(user_id)
		           .build();
		return user;
	}

}
