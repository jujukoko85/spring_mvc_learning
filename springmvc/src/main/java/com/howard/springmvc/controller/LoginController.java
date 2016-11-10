package com.howard.springmvc.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LoginController {
	
	public LoginController() {
		System.out.println("login controller is inited");
	}
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(new LoginFormValidator());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@Valid LoginForm loginForm, 
			Errors errors
			) {
		System.out.println("do login");
		System.out.println(loginForm.getUsername());
		System.out.println(loginForm.getPassword());
		
		return "test";
	}
}
