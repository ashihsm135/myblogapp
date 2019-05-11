package com.javasampleapproach.twitterbootstrap.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javasampleapproach.twitterbootstrap.exception.BadRequestException;
import com.javasampleapproach.twitterbootstrap.exception.ExpiredAuthenticationTokenException;
import com.javasampleapproach.twitterbootstrap.exception.SocioSeerException;
import com.javasampleapproach.twitterbootstrap.model.Response;
import com.javasampleapproach.twitterbootstrap.model.constants.Constant;

import com.javasampleapproach.twitterbootstrap.service.api.AuthenticationService;

@Configuration
public class ApplicationFilter implements Filter{

	private static Logger log = LoggerFactory.getLogger(ApplicationFilter.class);
	
	@Value("${socio.seer.auth.header}")
	private String authHeader;

	@Value("${socio.seer.secret.key}")
	private String secretKey;

	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		try {
				if(!httpRequest.getRequestURL().toString().contains("/auth/") &&
						(!httpRequest.getRequestURL().toString().contains("/"))){
					validateAuthToken(httpRequest.getHeader(authHeader));
				}
				
				
		} catch (Exception e) {
			writeErrorToResponse(e, response);
			return;
		}
		filterChain.doFilter(httpRequest, response);
	}

	private void validateAuthToken(String authToken) {

		  if (StringUtils.isEmpty(authToken)) {
		      log.error("Auth token not found in header.");
		      throw new BadRequestException("Auth token not found in header.");
		    }

		    Map<String, Object> response = authenticationService.validateAuthToken(authToken);
		    Map<String, Object> data = response;
		    if (response == null || CollectionUtils.isEmpty(data)) {
		      log.error("Could not obtain response from authentication service.");
		      throw new SocioSeerException("Could not obtain response from authentication service.");
		    }

		    boolean isTokenValid =
		        Boolean.valueOf(data.get(Constant.KEY_IS_AUTH_TOKEN_VALID).toString());
		    if (!isTokenValid) {
		      log.info("Authentication token has expired, please re-generate it.");
		      throw new ExpiredAuthenticationTokenException(
		          "Authentication token has expired, please re-generate it.");
		    }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	private void writeErrorToResponse(Exception e, ServletResponse servletResponse)
			throws IOException {

		Response<String> response = new Response<String>(HttpStatus.BAD_REQUEST.value(),
				e.getMessage(), null);

		try (PrintWriter writer = servletResponse.getWriter()) {
			writer.write(OBJECT_MAPPER.writeValueAsString(response));
			writer.flush();
		}

	}
	
	
}

