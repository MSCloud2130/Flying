package com.flying.seeker;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flying.seeker.Users.Seller;
import com.flying.seeker.Users.SellerRepository;
import com.flying.seeker.product.Product;
import com.flying.seeker.product.ProductRepository;
import com.flying.seeker.review.Review;

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

	/*
	 * @Bean CommandLineRunner runner(ProductRepository productRepository,
	 * SellerRepository sellerRepository) { return args -> {
	 * productRepository.deleteAll(); sellerRepository.deleteAll(); Date date = new
	 * Date(); Instant instant = Instant.now(); Seller seller = new Seller("name",
	 * "email", "password", "token", "img", null); sellerRepository.insert(seller);
	 * List<String> come = new ArrayList<>(); Review re = new Review();
	 * re.setComentary("value"); re.setStars(1); List<Review> reviews = new
	 * ArrayList<>(); come.add("e"); come.add("f"); Product product = new
	 * Product("name", 12.0, date.from(instant), "description", true,
	 * "place_arrival", "place_depature", "img", seller, come, null); //
	 * seller.addProduct(product); // sellerRepository.insert(seller);
	 * productRepository.insert(product); // Seller s = //
	 * sellerRepository.findById(product.getProduct_owner()).orElseThrow(); //
	 * logger.info(s.getEmail()); // productRepository. };
	 */

}
