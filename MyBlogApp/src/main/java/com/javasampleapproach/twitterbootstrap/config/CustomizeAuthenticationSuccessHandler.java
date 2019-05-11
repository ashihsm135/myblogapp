package com.javasampleapproach.twitterbootstrap.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.javasampleapproach.twitterbootstrap.model.User;
import com.javasampleapproach.twitterbootstrap.service.impl.CustomUserDetailService;

@Component
public class CustomizeAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	@Autowired
	private CustomUserDetailService userService;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		// set our response to OK status
		response.setStatus(HttpServletResponse.SC_OK);
        User user = userService.findUserByEmail(authentication.getName());
        request.getSession().setAttribute("user", user);
		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if ("ADMIN".equals(auth.getAuthority())) {
				response.sendRedirect("/mydashboard");
			}
		}
	}
}
