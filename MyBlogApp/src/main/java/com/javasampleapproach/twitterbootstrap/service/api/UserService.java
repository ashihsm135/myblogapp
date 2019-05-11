package com.javasampleapproach.twitterbootstrap.service.api;

import com.javasampleapproach.twitterbootstrap.model.User;

public interface UserService extends CrudApi<User> {

	public User getUserByEmail(String email);	
 
}
