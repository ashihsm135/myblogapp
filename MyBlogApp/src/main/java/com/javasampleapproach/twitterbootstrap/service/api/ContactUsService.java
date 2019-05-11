package com.javasampleapproach.twitterbootstrap.service.api;

import com.javasampleapproach.twitterbootstrap.model.ContactUs;


public interface ContactUsService extends CrudApi<ContactUs> {

	public ContactUs getContactUsByEmail(String email);	
 
}
