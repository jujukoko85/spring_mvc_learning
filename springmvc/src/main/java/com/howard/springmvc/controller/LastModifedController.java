package com.howard.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
public class LastModifedController {

	@RequestMapping("/lastModified")
	@ResponseBody
	public String lastModified(
			HttpServletRequest request,
			@RequestParam(name = "name", required = false)String name,
			HttpServletResponse response,
			WebRequest webRequest
			) {
		
//		DateTime lastModified = new DateTime(2016, 11, 24, 11, 51); 
		
		if(StringUtils.isEmpty(name)) {
			request.getSession().setAttribute("getNameTime", System.currentTimeMillis());
			return name;
		}
		
		// 如果checkNotModified为true,会在response中设置304
		// 如果checkNotModified为false,会在response中设置Last-Modified,下次请求头会增加 IF-MODIFIED-SINCE
		if(webRequest.checkNotModified(System.currentTimeMillis())) {
			System.out.println("######### not modified! ##########");
			return null;
		}
		
		return "hello";
	}
}
