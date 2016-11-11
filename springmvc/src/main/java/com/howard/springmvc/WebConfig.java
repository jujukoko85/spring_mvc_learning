package com.howard.springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.howard.springmvc.controller.ControllerConfig;

@Configuration
@EnableWebMvc /** <mvc:annotation-driven /> */
@ComponentScan(basePackageClasses = {ControllerConfig.class}) /** init the beans in package and subpackage of the config class */
@ImportResource(locations = {"WEB-INF/config/app-web-flow.xml"})
public class WebConfig extends WebMvcConfigurerAdapter {

	/** config the view resolve */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable(); /** config the static resources handler */
	}
}
