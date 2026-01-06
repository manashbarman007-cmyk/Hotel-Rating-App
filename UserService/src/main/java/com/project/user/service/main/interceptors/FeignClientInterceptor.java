package com.project.user.service.main.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignClientInterceptor implements RequestInterceptor{
	
	@Autowired
	private OAuth2AuthorizedClientManager clientManager; // we need to configure it



	// referred from Documentation 
	@Override
	public void apply(RequestTemplate template) {
		

		OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client") // the custom client name provided in
		                                                                                                                      // application.yml
		.principal("feign-client") // can be any string as in client_credentials grant type there is no user involvement, and principal means the 
		                           // authenticated user
		.build();
		
		// get the authorized client
	    OAuth2AuthorizedClient authorizedClient = clientManager.authorize(oAuth2AuthorizeRequest);
	    
	    if(authorizedClient != null && authorizedClient.getAccessToken() != null) {
	    	String accessToken = authorizedClient.getAccessToken().getTokenValue();
	    	template.header("Authorization", "Bearer " + accessToken);
	    }		
	}
}
