package com.project.hotel.service.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
	
	@Bean
	public SecurityFilterChain confFilterChain (HttpSecurity http) throws Exception {
		
		return http
				.authorizeHttpRequests(req ->
				req.anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 ->
				oauth2.jwt(Customizer.withDefaults()))
				.build();
	}

}
