package com.javasampleapproach.twitterbootstrap.api.dao.api;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.twitterbootstrap.model.User;


@Repository
public interface UserDao extends PagingAndSortingRepository<User, String> {
	
	User findOneByEmail(String email);
	
	List<User> findAll();
	
}
