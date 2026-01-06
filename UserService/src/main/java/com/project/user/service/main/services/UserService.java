package com.project.user.service.main.services;

import java.util.List;

import com.project.user.service.main.entities.User;

public interface UserService {
  
	public User insertUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(String id);
	
}

