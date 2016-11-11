package com.howard.springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600) /** default is 1800s */
public class RedisSessionConfig {

	@Bean
    public JedisConnectionFactory connectionFactory() {
		System.out.println("############################################");
		System.out.println("############################################");
		System.out.println("redis start...");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setHostName("192.168.1.193");
        System.out.println("redis inited...");
        System.out.println("############################################");
        System.out.println("############################################");
		return jedisConnectionFactory; 
    }
}
