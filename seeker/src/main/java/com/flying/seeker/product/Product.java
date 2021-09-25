package com.flying.seeker.product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.flying.seeker.Users.Seller;
import com.flying.seeker.review.Review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class Product {

    @Id
    private String _id;
    private String name;
    private Double price;
    private Date date;
    private String description;
    private Boolean isOnOffer;
    private String place_arrival;
    private String place_depature;
    private String img;
    private Seller product_owner;
    private List<String> categories = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public Product(String name, Double price, Date date, String description, Boolean isOnOffer, String place_arrival,
            String place_depature, String img, Seller product_owner, List<String> categories, List<Review> reviews) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.isOnOffer = isOnOffer;
        this.place_arrival = place_arrival;
        this.place_depature = place_depature;
        this.img = img;
        this.product_owner = product_owner;
        this.categories = categories;
        this.reviews = reviews;
    }

}
