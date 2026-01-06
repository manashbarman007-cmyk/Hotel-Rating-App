package com.project.user.service.main.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.user.service.main.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelClient {
	
	@GetMapping("/hotels/id/{hotelId}")
	public Hotel getHotelByHotelId(@PathVariable String hotelId);

}
