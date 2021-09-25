package com.flying.seeker.Users;

import java.util.ArrayList;
import java.util.List;

import com.flying.seeker.product.Product;
import com.flying.seeker.review.Review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Client {

    @Id
    private String _id;
    private String name;
    private String email;
    private String img;
    private String password;
    private String token;
    private List<Product> purchased_products = new ArrayList<>();
    private List<Review> my_reviews = new ArrayList<>();

    public Client(String name, String email, String img, String password, String token,
            List<Product> purchased_products, List<Review> my_reviews) {
        this.name = name;
        this.email = email;
        this.img = img;
        this.password = password;
        this.token = token;
        this.purchased_products = purchased_products;
        this.my_reviews = my_reviews;
    }

}
