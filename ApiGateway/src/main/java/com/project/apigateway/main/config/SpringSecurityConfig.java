package com.project.apigateway.main.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig {

	// For WebFLux we use SecurityWebFilterChain and ServerHttpSecurity 
	//instead of SecurityFilterChain and HttpSecurity
	@Bean
	public SecurityWebFilterChain configSpringSecurity(ServerHttpSecurity http) throws Exception {
	
		return http
				.authorizeExchange(requests ->
				requests
//				.pathMatchers("/users/public/**").permitAll()
				.anyExchange().authenticated()) // all other requests will be authenticated
				.oauth2Client(Customizer.withDefaults()) // shortcut that enables OAuth 2.0 login with default settings
                .oauth2ResourceServer((oauth2) -> 
                oauth2.jwt(Customizer.withDefaults())
                )
				.build();
	}
}
