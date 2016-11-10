package com.howard.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * SpringServletContainerInitializer implements ServletContainerInitializer
 * Web container will invoke SpringServletContainerInitializer from servlet 3.0
 * 
 * @author root
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer { //for jettyv

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("Get RootConfig");
		/** Core layer configuration class */
		return new Class<?>[]{
			RootConfig.class,
			RedisSessionConfig.class
			};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		logger.info("Get ServletConfig");
		/** Web layer configuration class */
		return new Class<?>[]{
			WebConfig.class
			};
	}

	@Override
	protected String[] getServletMappings() {
		logger.info("Get ServletMapping");
		/** map '/' to DispatchServlet */
		return new String[]{"/"};
	}

}
