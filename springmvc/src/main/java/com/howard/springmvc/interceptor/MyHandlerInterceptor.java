package com.howard.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("#############");
		System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getStackTrace()[2].getMethodName());
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("#############");
		System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getStackTrace()[2].getMethodName());
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("#############");
		System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getStackTrace()[2].getMethodName());
	}

}
