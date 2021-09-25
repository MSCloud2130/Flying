package com.flying.seekerClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeekerClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeekerClientApplication.class, args);
	}

}
