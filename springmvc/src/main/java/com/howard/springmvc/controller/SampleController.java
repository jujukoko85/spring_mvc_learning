package com.howard.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SampleController {

	public SampleController() {
		System.out.println("######################");
		System.out.println("Controller is inited!");
	}
	
	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public String sample(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(session);
		return "test";
	}
}
