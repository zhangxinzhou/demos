#  # 说明
##
nginx无需改动,使用springboot内置Tomcat也无需配置Tomcat,pom.xml中加入

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session</artifactId>
		</dependency>

同时增加一个RedisSessionConfig

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

完成!
