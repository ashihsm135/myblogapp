package com.javasampleapproach.twitterbootstrap.exception;

public class ExpiredAuthenticationTokenException extends RuntimeException {

	private static final long serialVersionUID = -2183772193871218284L;

	public ExpiredAuthenticationTokenException() {
	}

	public ExpiredAuthenticationTokenException(String message) {
		super(message);
	}

	public ExpiredAuthenticationTokenException(Throwable cause) {
		super(cause);
	}

	public ExpiredAuthenticationTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpiredAuthenticationTokenException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
