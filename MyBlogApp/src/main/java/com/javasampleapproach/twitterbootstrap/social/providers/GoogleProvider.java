package com.javasampleapproach.twitterbootstrap.social.providers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.javasampleapproach.twitterbootstrap.model.UserBean;


@Service
public class GoogleProvider   {

	private static final String REDIRECT_CONNECT_GOOGLE = "redirect:/login";
	private static final String GOOGLE = "google";

   	@Autowired
    	BaseProvider socialLoginBean ;


	public String getGoogleUserData(Model model, UserBean userForm,HttpServletRequest request) {

		ConnectionRepository connectionRepository = socialLoginBean.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(Google.class) == null) {
			return REDIRECT_CONNECT_GOOGLE;
		}

		populateUserDetailsFromGoogle(userForm);
		if(request.getSession().getAttribute("existingUser") != null){
			request.getSession().removeAttribute("existingUser");
		}
		request.getSession().setAttribute("existingUser", userForm);
	   //model.addAttribute("loggedInUser",userForm);
		return "dashboard";
	}

	
	protected void populateUserDetailsFromGoogle(UserBean userform) {
		Google google = socialLoginBean.getGoogle();
		Person googleUser = google.plusOperations().getGoogleProfile();
		userform.setEmail(googleUser.getAccountEmail());
		userform.setFirstName(googleUser.getGivenName());
		userform.setLastName(googleUser.getFamilyName());
		userform.setImage(googleUser.getImageUrl());
		userform.setProvider(GOOGLE);
	}

}
