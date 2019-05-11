package com.javasampleapproach.twitterbootstrap.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.javasampleapproach.twitterbootstrap.model.ContactUs;
import com.javasampleapproach.twitterbootstrap.model.User;
import com.javasampleapproach.twitterbootstrap.service.api.ContactUsService;
import com.javasampleapproach.twitterbootstrap.service.api.UserService;
import com.javasampleapproach.twitterbootstrap.service.impl.CustomUserDetailService;

@Controller
public class LoginController {

	UserService userService;

	@Autowired
	private CustomUserDetailService userServicecus;

	@Autowired
	private ContactUsService contactUsService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav) {
		mav.addObject("user", new User()).setViewName("login");;
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(ModelAndView mav) {
		mav.addObject("user", new User()).setViewName("register");;
		return mav;
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotpassword() {
		return "forgot-password";
	}

	@RequestMapping(value = "/facebook/feeds", method = RequestMethod.GET)
	public String facebookfeeds() {
		return "forgot-password";
	}

	@RequestMapping(value = "/blog/springboot", method = RequestMethod.GET)
	public ModelAndView linkedinConnections(ModelAndView mav) {
		mav.setViewName("blog/springboot");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/mydashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userServicecus.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user);
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}

	@RequestMapping(value = "/blank", method = RequestMethod.GET)
	public String blank() {

		return "blank";
	}

	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public ModelAndView contactus(ModelAndView mav) {

		mav.addObject("contactus",new ContactUs());
		mav.setViewName("contactus");
		return mav;
	}

	@RequestMapping(value = "/contactus", method = RequestMethod.POST)
	public ModelAndView contactus(ContactUs contactUs) {

		ModelAndView mav = new ModelAndView();
		contactUs = contactUsService.save(contactUs);
		mav.setViewName("contactus");
		return mav;

	}
}
