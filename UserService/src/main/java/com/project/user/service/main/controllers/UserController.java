package com.project.user.service.main.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.user.service.main.entities.Rating;
import com.project.user.service.main.entities.User;
import com.project.user.service.main.services.UserServiceImpl;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	// create user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User insertedUser = userServiceImpl.insertUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(insertedUser);
	}
	
	// get all users
	@GetMapping
	@RateLimiter(name = "myRateLimiter1", fallbackMethod = "fallback1")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users =userServiceImpl.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	public ResponseEntity<List<User>> fallback1(Throwable t) {
		log.info("Exception due to : " + t.getMessage());
		User user = User.builder()
				.userId("123")
				.email("dummy@dummy.com")
				.name("dummy")
				.build();
		List<User> users = List.of(user);
		return new ResponseEntity<List<User>>(users, HttpStatus.BAD_REQUEST);
	}
	
	// get user by id
	@GetMapping("/id/{user_id}")
	@RateLimiter(name = "myRateLimiter2", fallbackMethod = "fallback2")
	 public ResponseEntity<User> getUserById(@PathVariable String user_id) {
		 User user = userServiceImpl.getUserById(user_id);
		 return ResponseEntity.ok(user);
	 }
	
	public ResponseEntity<User> fallback2(String user_id, Throwable t) {
		log.info("Exception due to : " + t.getMessage());
		User user = User.builder()
				.userId(user_id)
				.email("dummy@dummy.com")
				.name("dummy")
				.build();
		return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
	}

	
}
