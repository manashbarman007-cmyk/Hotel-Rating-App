package com.project.rating.service.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.rating.service.main.entity.Rating;

@Repository
public interface RatingRepo extends MongoRepository<Rating, String>{

}
