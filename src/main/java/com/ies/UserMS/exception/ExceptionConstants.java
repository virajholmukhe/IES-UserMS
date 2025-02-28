package com.ies.UserMS.exception;

import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:messages.properties")
public enum ExceptionConstants {

	EMAIL_ALREADY_USED("email.already.used"),
	EMAIL_NOT_EXISTS("email.not.exists"),
	INVALID_PASSWORD("password.invalid"),
	PASSWORD_NOT_AVAILABLE("password.not.available"),
	INVALID_TEMP_PASSWORD("temp.password.invalid"),
	USER_IS_NOT_CREATED("user.not.created");

	private final String type;
	
	private ExceptionConstants(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}
