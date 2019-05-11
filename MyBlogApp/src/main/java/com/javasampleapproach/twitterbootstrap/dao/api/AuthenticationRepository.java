package com.javasampleapproach.twitterbootstrap.dao.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javasampleapproach.twitterbootstrap.model.AuthenticationToken;

public interface AuthenticationRepository extends MongoRepository<AuthenticationToken, String> {

	AuthenticationToken findOneByUserId(String userId);

	void deleteByUserId(String userId);

	AuthenticationToken findOneByAuthToken(String authToken);
}
