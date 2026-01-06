package com.project.user.service.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.user.service.main.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	

}
