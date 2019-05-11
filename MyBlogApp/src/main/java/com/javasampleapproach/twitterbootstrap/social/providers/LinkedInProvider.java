package com.javasampleapproach.twitterbootstrap.social.providers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.javasampleapproach.twitterbootstrap.model.UserBean;

@Service
public class LinkedInProvider  {

	private static final String LINKED_IN = "linkedIn";
	
	private static final String REDIRECT_LOGIN = "redirect:/login";

  	@Autowired
    	BaseProvider socialLoginBean ;

	public String getLinkedInUserData(Model model, UserBean userForm,HttpServletRequest request) {

		ConnectionRepository connectionRepository = socialLoginBean.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
			return REDIRECT_LOGIN;
		}
		populateUserDetailsFromLinkedIn(userForm);
		if(request.getSession().getAttribute("existingUser") != null){
			request.getSession().removeAttribute("existingUser");
		}
		request.getSession().setAttribute("existingUser", userForm);
		return "dashboard";
	}
	
	public String getLinkedInConnections(Model model) {

		ConnectionRepository connectionRepository = socialLoginBean.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
			return REDIRECT_LOGIN;
		}
		LinkedIn linkedIn = socialLoginBean.getLinkedIn(); 
		List<LinkedInProfile> connections = linkedIn.connectionOperations().getConnections();
		model.addAttribute("connections",connections);
		return "connections";
	}
	
	private void populateUserDetailsFromLinkedIn(UserBean userForm) {
		LinkedIn linkedIn = socialLoginBean.getLinkedIn();
		LinkedInProfileFull linkedInUser = linkedIn.profileOperations().getUserProfileFull();
		userForm.setEmail(linkedInUser.getEmailAddress());
		userForm.setFirstName(linkedInUser.getFirstName());
		userForm.setLastName(linkedInUser.getLastName());
		userForm.setImage(linkedInUser.getProfilePictureUrl());
		userForm.setProvider(LINKED_IN);
	}

}
