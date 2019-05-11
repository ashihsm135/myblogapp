package com.javasampleapproach.twitterbootstrap.exception;

public class InvalidAccessTokenException extends RuntimeException {

	private static final long serialVersionUID = -3207055787488461503L;

	public InvalidAccessTokenException() {
	}

	public InvalidAccessTokenException(String message) {
		super(message);
	}

	public InvalidAccessTokenException(Throwable cause) {
		super(cause);
	}

	public InvalidAccessTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAccessTokenException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
