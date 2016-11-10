package com.howard.springmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.howard.springmvc.service.ServiceConfig;

@Configuration
@ComponentScan(
		basePackageClasses = {ServiceConfig.class}
//		basePackages = {"com.howard.springmvc.service"},
//		excludeFilters = {
//				@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
//				}
		)
public class RootConfig {

}
