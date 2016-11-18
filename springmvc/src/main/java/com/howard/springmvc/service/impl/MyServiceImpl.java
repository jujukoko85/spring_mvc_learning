package com.howard.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.howard.springmvc.service.MyService;

@Service("myService")
public class MyServiceImpl implements MyService {

	public MyServiceImpl() {
		System.out.println("#################");
		System.out.println("myService inited!");
		System.out.println("#################");
	}
}
