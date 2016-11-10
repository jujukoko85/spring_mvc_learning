package com.howard.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.howard.springmvc.service.MyService;

@Service("myService")
public class MyServiceImpl implements MyService {

	public MyServiceImpl() {
		System.out.println("myService inited!");
	}
}
