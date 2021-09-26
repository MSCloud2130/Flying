package com.flying.cart.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flying.cart.Review.Review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {

    @Id
    private String _id;
    private String name;
    private String transport;
    private String accommodation;
    private String food_service;
    private Double price;
    private Date date;
    private String description;
    private Boolean isOnOffer;
    private String place_arrival;
    private String place_depature;
    private String img;
    private List<String> categories = new ArrayList<>();
    @DBRef
    private List<Review> review = new ArrayList<>();

    public Product() {
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getFood_service() {
        return food_service;
    }

    public void setFood_service(String food_service) {
        this.food_service = food_service;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsOnOffer() {
        return isOnOffer;
    }

    public void setIsOnOffer(Boolean isOnOffer) {
        this.isOnOffer = isOnOffer;
    }

    public String getPlace_arrival() {
        return place_arrival;
    }

    public void setPlace_arrival(String place_arrival) {
        this.place_arrival = place_arrival;
    }

    public String getPlace_depature() {
        return place_depature;
    }

    public void setPlace_depature(String place_depature) {
        this.place_depature = place_depature;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
