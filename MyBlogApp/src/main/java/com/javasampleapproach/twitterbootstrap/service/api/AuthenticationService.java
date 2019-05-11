package com.javasampleapproach.twitterbootstrap.service.api;

import java.util.Map;

import com.javasampleapproach.twitterbootstrap.model.AuthenticationToken;

public interface AuthenticationService {

	AuthenticationToken generateToken(String email, String password, String client);

	AuthenticationToken getAuthenticationTokenByUserId(String userId);

	void deleteAuthenticationTokenByUserId(String userId);
	
	Map<String, Object> validateAuthToken(String authenticationToken);
}
