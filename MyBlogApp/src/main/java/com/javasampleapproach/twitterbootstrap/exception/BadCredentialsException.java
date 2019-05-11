package com.javasampleapproach.twitterbootstrap.exception;

public class BadCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1932603799876896358L;

	public BadCredentialsException() {
	}

	public BadCredentialsException(String message) {
		super(message);
	}

	public BadCredentialsException(Throwable cause) {
		super(cause);
	}

	public BadCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadCredentialsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
