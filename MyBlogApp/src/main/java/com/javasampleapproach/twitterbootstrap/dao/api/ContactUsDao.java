package com.javasampleapproach.twitterbootstrap.dao.api;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.twitterbootstrap.model.ContactUs;

@Repository
public interface ContactUsDao extends PagingAndSortingRepository<ContactUs, String> {
	
	ContactUs findOneByEmail(String email);
	
}