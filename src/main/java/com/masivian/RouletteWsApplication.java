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
	


	public static void main(String[] args) {
		SpringApplication.run(RouletteWsApplication.class, args);
	}

}
