package com.flying.product.publication.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flying.product.publication.Review.Review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {

    @Id
    private String _id;
    private String name;
    private String description;
    private Double price;
    private Boolean isOnOffer;
    private String category;
    private String img;
    private String country;
    @DBRef
    private List<Review> review = new ArrayList<>();

    private String place_arrival;
    private String place_depature;
    private Date date_arrival;
    private Date date_depature;
    private String transport;
    private String accommodation;
    private String food_service;
    private String guide;

    public Product() {
    }

    public String get_id() {
        return _id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsOnOffer() {
        return isOnOffer;
    }

    public void setIsOnOffer(Boolean isOnOffer) {
        this.isOnOffer = isOnOffer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Date getDate_arrival() {
        return date_arrival;
    }

    public void setDate_arrival(Date date_arrival) {
        this.date_arrival = date_arrival;
    }

    public Date getDate_depature() {
        return date_depature;
    }

    public void setDate_depature(Date date_depature) {
        this.date_depature = date_depature;
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

}
