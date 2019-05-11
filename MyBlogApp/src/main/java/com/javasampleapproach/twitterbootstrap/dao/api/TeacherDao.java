package com.javasampleapproach.twitterbootstrap.dao.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.twitterbootstrap.model.Teacher;


/**
 * Mixing Custom repository with {@link MongoRepository} can benefit us both of
 * the paradigms.
 *
 * @author Ashish Mishra
 */
@Repository
public interface TeacherDao extends PagingAndSortingRepository<Teacher, String> {
	
	Teacher findOneByEmail(String email);
	
	
}
