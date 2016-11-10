package com.howard.springmvc.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return LoginForm.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", null, "Username is empty.");
		ValidationUtils.rejectIfEmpty(errors, "password", null, "Password is empty.");
	}

}
