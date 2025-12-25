package com.project.user.service.main.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
	
	private String id; // we can also use ObjectId
	
	private String name;
	
	private String location;
	
	private String about;
}

