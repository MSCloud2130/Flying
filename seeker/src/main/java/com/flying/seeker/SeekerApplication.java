package com.flying.seeker;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flying.seeker.Users.Client;
import com.flying.seeker.Users.ClientRepository;
import com.flying.seeker.Users.Seller;
import com.flying.seeker.Users.SellerRepository;
import com.flying.seeker.product.Product;
import com.flying.seeker.product.ProductRepository;
import com.flying.seeker.review.Review;
import com.flying.seeker.review.ReviewRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SeekerApplication {
	Logger logger = LoggerFactory.getLogger(SeekerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SeekerApplication.class, args);
	}

}
