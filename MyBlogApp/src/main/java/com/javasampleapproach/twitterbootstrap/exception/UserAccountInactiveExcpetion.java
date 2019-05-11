package com.javasampleapproach.twitterbootstrap.exception;

public class UserAccountInactiveExcpetion extends RuntimeException {

	private static final long serialVersionUID = -3140390393308363387L;

	public UserAccountInactiveExcpetion() {
	}

	public UserAccountInactiveExcpetion(String message) {
		super(message);
	}

	public UserAccountInactiveExcpetion(Throwable cause) {
		super(cause);
	}

	public UserAccountInactiveExcpetion(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAccountInactiveExcpetion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
