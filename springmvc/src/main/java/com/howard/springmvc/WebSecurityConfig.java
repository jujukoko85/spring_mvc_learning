package com.howard.springmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //自动识别是否是SpringMVC
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}
}

/**

默认过滤器链
FilterChainProxy[
	Filter Chains: [
		[ org.springframework.security.web.util.matcher.AnyRequestMatcher@1, 
			[
				org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@5e06130f, 
				org.springframework.security.web.context.SecurityContextPersistenceFilter@6327c59f, 
				org.springframework.security.web.header.HeaderWriterFilter@3ad54d4d, 
				org.springframework.security.web.csrf.CsrfFilter@558182c7, 
				org.springframework.security.web.authentication.logout.LogoutFilter@5e0fc70e, 
				org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@4f43a3e7, 
				org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@77ac59d6, 
				org.springframework.security.web.authentication.www.BasicAuthenticationFilter@10fa5c30, 
				org.springframework.security.web.savedrequest.RequestCacheAwareFilter@751274c0, 
				org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@7d39e472, 
				org.springframework.security.web.authentication.AnonymousAuthenticationFilter@60cd6e02, 
				org.springframework.security.web.session.SessionManagementFilter@7d3da8a5, 
				org.springframework.security.web.access.ExceptionTranslationFilter@5429647c, 
				org.springframework.security.web.access.intercept.FilterSecurityInterceptor@391ecf28
			]
		]
	]
]


*/