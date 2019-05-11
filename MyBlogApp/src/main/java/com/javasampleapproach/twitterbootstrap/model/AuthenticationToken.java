package com.javasampleapproach.twitterbootstrap.model;


import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(collection = "authenticationToken")
@JsonInclude(content = Include.NON_NULL)
public class AuthenticationToken {

	public AuthenticationToken(String id,String authToken,String client,long creatDate) {
	  this.id = id;
	  this.authToken = authToken;
	  this.client = client;
	  this.creatDate = creatDate;
	}
	
	private String id;
	private String userId;
	private String authToken;
	private String client;
	private long creatDate;
	private long lastAccessed;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	public String getUserId() {
		return userId;
	}
	public String getAuthToken() {
		return authToken;
	}
	public String getClient() {
		return client;
	}
	public long getCreatDate() {
		return creatDate;
	}
}
