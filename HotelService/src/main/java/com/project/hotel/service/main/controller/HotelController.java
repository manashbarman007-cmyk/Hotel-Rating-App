package com.project.hotel.service.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotel.service.main.entities.Hotel;
import com.project.hotel.service.main.service.HotelServiceImpl;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelServiceImpl impl;
	
	@PostMapping
	public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
		Hotel savedHotel = impl.insertHotel(hotel);
		return ResponseEntity.ok(savedHotel);
	}
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_internal')") // the custom scope that we created
	                                                // Only the UserService has the "internal" scope (even the ApiGateway
	                                                // does not have it). Thus, only the UserService can access this API
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> allHotels = impl.getAllHotels();
		return ResponseEntity.ok(allHotels);
	}
	
	
	@GetMapping("/id/{hotelId}")
	@PreAuthorize("hasAuthority('SCOPE_internal')") // the custom scope that we defined
                                                    // Only the UserService has the "internal" scope (even the ApiGateway)
                                                    // does not have it. Thus, only the UserService can access this API
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
		Hotel hotel = impl.getHotelById(hotelId);
		return ResponseEntity.ok(hotel);
	}

}
