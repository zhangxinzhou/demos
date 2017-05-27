package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60)//session过期时间
@Configuration
public class RedisSessionConfig {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}
}
