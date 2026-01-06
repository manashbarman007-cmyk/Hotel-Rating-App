package com.project.apigateway.main.controllers;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.apigateway.main.models.AuthResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			                            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			                            @AuthenticationPrincipal OidcUser user			                            
			                            ) {
		log.info("User email : " + user.getEmail());
		
		// user.getAuthorities() returns a Collection of GrantedAuthority
		// we will convert Collection of GrantedAuthority to a Collection of String
		List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority())
		                           .collect(Collectors.toList());
		
		AuthResponse authResponse = AuthResponse.builder()
				                    .userID(user.getEmail())
				                    .accessToken(client.getAccessToken().getTokenValue())
				                    .refreshToken(client.getRefreshToken().getTokenValue())
				                    .expireAt(client.getAccessToken().getExpiresAt().getEpochSecond())
				                    .authorities(authorities)
				                    .build();
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

}
