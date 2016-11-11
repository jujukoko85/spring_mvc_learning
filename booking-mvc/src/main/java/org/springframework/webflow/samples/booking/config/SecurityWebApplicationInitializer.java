package org.springframework.webflow.samples.booking.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * ServletContext initializer for Spring Security specific configuration such as
 * the chain of Spring Security filters.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer implements WebApplicationInitializer {
}
