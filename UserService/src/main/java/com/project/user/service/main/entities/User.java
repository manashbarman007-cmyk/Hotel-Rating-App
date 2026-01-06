package com.project.user.service.main.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "micro_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	private String userId; // we won't auto generate
	
	private String name;
	
	private String email;
	
	@Transient // this field won't be stored in db
	private List<Rating> ratings = new ArrayList<>(); // we will get this from the rating-service
	

}
