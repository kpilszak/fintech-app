package com.kpilszak.security.user.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException() {
		super("User not found");
	}
}
