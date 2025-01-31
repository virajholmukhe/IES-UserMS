package com.ies.UserMS.exception;

public class UserMSException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UserMSException(String errors) {
		super(errors);
	}
}
