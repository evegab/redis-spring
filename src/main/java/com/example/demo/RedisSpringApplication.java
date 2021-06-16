package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class RedisSpringApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(RedisSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RedisSpringApplication.class, args);
        LOG.info("Springboot redis application is started successfully.");
	}

}
