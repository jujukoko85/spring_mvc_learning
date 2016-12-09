package com.howard.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;

public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer
implements WebApplicationInitializer
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public WebSecurityInitializer() {
		logger.info("WebSecurityInitializer inited!");
	}

}
