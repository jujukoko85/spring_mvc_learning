package com.howard.springmvc.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginFormValidator implements Validator {

	private static final String CODE_USERNAME_IS_NULL = "username_is_null";
	private static final String CODE_PASSWORD_IS_NULL = "password_is_null";
	
	@Override
	public boolean supports(Class<?> arg0) {
		return LoginForm.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", CODE_USERNAME_IS_NULL, "Username is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", CODE_PASSWORD_IS_NULL, "Password is empty.");
	}

}
