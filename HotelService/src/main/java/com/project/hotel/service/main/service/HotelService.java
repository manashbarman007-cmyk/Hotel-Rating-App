package com.project.hotel.service.main.service;

import java.util.List;

import com.project.hotel.service.main.entities.Hotel;

public interface HotelService {
	
	public Hotel insertHotel(Hotel hotel);
	
	public List<Hotel> getAllHotels();
	
	public Hotel getHotelById(String id);

}
