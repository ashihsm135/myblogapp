package com.javasampleapproach.twitterbootstrap;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.javasampleapproach.twitterbootstrap.exception.SocioSeerException;
import com.javasampleapproach.twitterbootstrap.model.Response;

/**
 * The Class SocioSeerRestAppExceptionHandler.
 */

@ControllerAdvice
public class SocioSeerRestAppExceptionHandler {

	/**
	 * Handle socio seer exception.
	 *
	 * @param e
	 *            the e
	 * @return the response entity
	 */
	@ExceptionHandler(SocioSeerException.class)
	public ResponseEntity<Response<String>> handleSocioSeerException(Exception e) {
		return new ResponseEntity<Response<String>>(
				new Response<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle illegal argument exception.
	 *
	 * @param e
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Response<String>> handleIllegalArgumentException(
			Exception e) {
		return new ResponseEntity<Response<String>>(new Response<String>(
				HttpStatus.BAD_REQUEST.value(), e.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error/error");
		return mav;
	}

}
