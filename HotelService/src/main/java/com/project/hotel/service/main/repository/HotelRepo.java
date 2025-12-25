package com.project.hotel.service.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.hotel.service.main.entities.Hotel;

@Repository
public interface HotelRepo extends MongoRepository<Hotel, String>{

}
