package com.howard.springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60) /** default is 1800s */
public class RedisSessionConfig {

	@Bean
    public JedisConnectionFactory connectionFactory() {
		System.out.println("############################################");
		System.out.println("############################################");
		System.out.println("redis start...");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setHostName("127.0.0.1");
        System.out.println("redis inited...");
        System.out.println("############################################");
        System.out.println("############################################");
		return jedisConnectionFactory; 
    }
}

/**

	web.xml
	<filter>  
        <filter-name>springSessionRepositoryFilter</filter-name>  
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>  
    </filter>  
    <filter-mapping>  
        <filter-name>springSessionRepositoryFilter</filter-name>  
        <url-pattern>/*</url-pattern>  
    </filter-mapping>  
    <session-config>
    	<!--分钟为单位-->
        <session-timeout>30</session-timeout>  
    </session-config>   

	app-context.xml
	<bean class="org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration">   
        <!-- 过期时间100分钟 -->  
        <property name="maxInactiveIntervalInSeconds" value="6000"></property>  
    </bean>    
    <!-- redis连接池 -->  
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig" />   
          
    <bean  id="jedisConnectionFactory"  class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory"  >    
        <property  name="hostName"  value="10.4.120.180" />    
        <property  name="port"  value="6379" />    
        <property  name="poolConfig"  ref="jedisPoolConfig" />    
    </bean>   

**/