package com.javasampleapproach.twitterbootstrap.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.twitterbootstrap.model.AuthenticationToken;
import com.javasampleapproach.twitterbootstrap.model.Response;
import com.javasampleapproach.twitterbootstrap.model.User;
import com.javasampleapproach.twitterbootstrap.service.api.AuthenticationService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<AuthenticationToken>> generateAuthToken(@RequestBody User user) {
		return new ResponseEntity<Response<AuthenticationToken>>(new Response<AuthenticationToken>(
				HttpStatus.OK.value(), "Authentication token generated successfully.",
				authenticationService.generateToken(user.getEmail(), user.getPassword(), null)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "{authToken:.+}", method = RequestMethod.POST)
	public ResponseEntity<Response<Map<String, Object>>> validateAuthToken(
			@PathVariable(name = "authToken", required = true) String authToken) {
		return new ResponseEntity<Response<Map<String, Object>>>(new Response<Map<String, Object>>(
				HttpStatus.OK.value(), "Authentication token validated successfully.",
				authenticationService.validateAuthToken(authToken)), HttpStatus.OK);
	}

	@RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Response<Boolean>> deleteAuthToken(
			@PathVariable(name = "userId", required = true) String userId) {
		authenticationService.deleteAuthenticationTokenByUserId(userId);
		return new ResponseEntity<Response<Boolean>>(new Response<Boolean>(HttpStatus.OK.value(),
				"Authentication token deleted successfully.", null), HttpStatus.OK);
	}
}
