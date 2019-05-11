package com.javasampleapproach.twitterbootstrap.social.providers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.javasampleapproach.twitterbootstrap.model.UserBean;


@Service
public class FacebookProvider  {

	private static final String FACEBOOK = "facebook";
	private static final String REDIRECT_LOGIN = "redirect:/login";

    	@Autowired
    	BaseProvider baseProvider ;
    	

	public String getFacebookUserData(Model model, UserBean userForm,HttpServletRequest request) {

		ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return REDIRECT_LOGIN;
		}
		populateUserDetailsFromFacebook(userForm);
		if(request.getSession().getAttribute("existingUser") != null){
			request.getSession().removeAttribute("existingUser");
		}
		request.getSession().setAttribute("existingUser", userForm);
		/*model.addAttribute("loggedInUser",userForm);*/
		return "dashboard";
	}

	protected void populateUserDetailsFromFacebook(UserBean userForm) {
		Facebook facebook = baseProvider.getFacebook();
		User user = facebook.userOperations().getUserProfile();
		userForm.setEmail(user.getEmail());
		userForm.setFirstName(user.getFirstName());
		userForm.setLastName(user.getLastName());
		userForm.setImage(user.getCover().getSource());
		userForm.setProvider(FACEBOOK);
	}
	
	public String getFacebookFeedData(Model model, UserBean userForm) {

		ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return REDIRECT_LOGIN;
		}
		Facebook facebook = baseProvider.getFacebook();
		//User user = facebook.userOperations().getUserProfile();
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
		return "facebook/feeds";
	}

	protected void populateUserFeedsFromFacebook(UserBean userForm) {
		
	}

	 

}
