package com.masivian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.lang.Object;

@SpringBootApplication
@EnableRedisRepositories
public class RouletteWsApplication {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}	
	
	@Bean
	RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RouletteWsApplication.class, args);
	}

}
