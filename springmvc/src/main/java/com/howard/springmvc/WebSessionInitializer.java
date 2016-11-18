package com.howard.springmvc;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * add springSessionRepositoryFilter(inited by RedisSessionConfig in root content) to servlet context
 * @author Administrator
 *
 */
public class WebSessionInitializer extends AbstractHttpSessionApplicationInitializer 
implements WebApplicationInitializer 
{

}
