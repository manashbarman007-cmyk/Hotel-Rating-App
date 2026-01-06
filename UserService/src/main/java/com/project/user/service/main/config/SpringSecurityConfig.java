package com.project.user.service.main.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
	
	@Bean
	public SecurityFilterChain confSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(req ->
				req
				.anyRequest().authenticated())
				.oauth2Client(Customizer.withDefaults())
				.oauth2ResourceServer(oauth ->
				oauth.jwt(Customizer.withDefaults()))
				.build();
	}

}
