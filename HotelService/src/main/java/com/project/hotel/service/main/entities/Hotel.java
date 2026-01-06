package com.project.hotel.service.main.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "micro_hotels")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
	
	@Id
	private String id; // we can also use ObjectId
	
	private String name;
	
	private String location;
	
	private String about;
}
