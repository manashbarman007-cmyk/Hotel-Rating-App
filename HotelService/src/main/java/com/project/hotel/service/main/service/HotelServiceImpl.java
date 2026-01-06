package com.project.hotel.service.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel.service.main.entities.Hotel;
import com.project.hotel.service.main.exception.HotelNotFoundException;
import com.project.hotel.service.main.repository.HotelRepo;


@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelRepo hotelRepo;
	
	@Override
	public Hotel insertHotel(Hotel hotel) {
		Hotel savedHotel = hotelRepo.save(hotel);
		return savedHotel;
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> allHotels = hotelRepo.findAll();
		return allHotels;
	}

	@Override
	public Hotel getHotelById(String id) {
		Hotel hotel = hotelRepo.findById(id).orElseThrow(
				() -> new HotelNotFoundException("No hotel found with id : " + id)); 
				     
		return hotel;
	}

}
