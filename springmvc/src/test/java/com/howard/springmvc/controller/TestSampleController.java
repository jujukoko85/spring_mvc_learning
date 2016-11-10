package com.howard.springmvc.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.howard.springmvc.RootConfig;
import com.howard.springmvc.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({  
	@ContextConfiguration(name = "parent", classes = RootConfig.class),  
	@ContextConfiguration(name = "child", classes = WebConfig.class)  
})  
public class TestSampleController {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    @Before
    public void before() {
    	request = new MockHttpServletRequest();
    	response = new MockHttpServletResponse();
    	request.setCharacterEncoding("UTF-8");
    	
    }

	@Test
	public void test1() {
		SampleController sampleController = new SampleController();
		MockMvc mockMvcStandalone = standaloneSetup(sampleController).build();
		
		try {
			mockMvcStandalone.perform(get("/sample")).andExpect(view().name("test"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void test2() {
		MockMvc mockMvc = webAppContextSetup(wac).build();
		try {
			mockMvc.perform(get("/sample")).andExpect(view().name("test"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
