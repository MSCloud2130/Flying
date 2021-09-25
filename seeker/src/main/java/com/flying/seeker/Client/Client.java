package com.flying.seeker.Client;

import java.util.ArrayList;
import java.util.List;

import com.flying.seeker.product.Product;
import com.flying.seeker.review.Review;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Client {

    private String name;
    private String email;
    private String img;
    private String password;
    private String token;
    private List<Product> purchased_products = new ArrayList<>();
    private List<Review> my_reviews = new ArrayList<>();
}
