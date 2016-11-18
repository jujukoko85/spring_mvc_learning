package com.howard.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyHandlerInterceptor2 extends HandlerInterceptorAdapter {

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("#############");
		System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getStackTrace()[2].getMethodName());
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("#############");
		System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("#############");
		System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("#############");
		System.out.println(this.getClass().getName() + " : " + Thread.currentThread().getStackTrace()[2].getMethodName());
	}
}
