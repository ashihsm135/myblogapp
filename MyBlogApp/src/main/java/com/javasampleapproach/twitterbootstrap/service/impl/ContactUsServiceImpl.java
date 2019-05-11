package com.javasampleapproach.twitterbootstrap.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javasampleapproach.twitterbootstrap.dao.api.ContactUsDao;
import com.javasampleapproach.twitterbootstrap.exception.SocioSeerException;
import com.javasampleapproach.twitterbootstrap.model.ContactUs;
import com.javasampleapproach.twitterbootstrap.service.api.ContactUsService;

@Slf4j
@Service
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	private ContactUsDao contactUsDao;

	@Override
	public ContactUs getContactUsByEmail(String email) {

		try {
			ContactUs contactUs = contactUsDao.findOneByEmail(email);
			return contactUs;
		} catch (Exception e) {
			String message = String.format(
					"Error while fetching contactUs by email : %s", email);
			log.debug(message);
			throw new SocioSeerException(message, e);
		}
	}

	@Override
	public ContactUs save(ContactUs contactUs) {
		ContactUs existingContactUs = getContactUsByEmail(contactUs.getEmail());
		if (existingContactUs != null) {
			String message = String.format(
					"There already exists an user with email : %s",
					existingContactUs.getEmail());
			throw new IllegalArgumentException(message);
		}
		try {
			return contactUsDao.save(contactUs);
		} catch (Exception e) {
			String message = String.format(
					"Error while saving user with email : %s",
					contactUs.getEmail());
			throw new SocioSeerException(message, e);
		}
	}

	@Override
	public ContactUs update(String id, ContactUs entity) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactUs get(String id) {

		// TODO Auto-generated method stub
		return null;
	}

}
