package com.javasampleapproach.twitterbootstrap.dao.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.twitterbootstrap.model.User;


/**
 * Mixing Custom repository with {@link MongoRepository} can benefit us both of
 * the paradigms.
 *
 * @author Ashish Mishra
 */
@Repository
public interface UserDao extends PagingAndSortingRepository<User, String> {
	
	User findOneByEmail(String email);

}
