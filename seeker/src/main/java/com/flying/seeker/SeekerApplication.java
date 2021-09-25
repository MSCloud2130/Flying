package com.flying.seeker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeekerApplication {
	Logger logger = LoggerFactory.getLogger(SeekerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SeekerApplication.class, args);
	}

}
