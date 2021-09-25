package com.flying.seeker.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flying.seeker.review.Review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
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
    private List<String> categories = new ArrayList<>();
    @DBRef
    private List<Review> review = new ArrayList<>();

    public Product(String name, Double price, Date date, String description, Boolean isOnOffer, String place_arrival,
            String place_depature, String img, List<String> categories) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.isOnOffer = isOnOffer;
        this.place_arrival = place_arrival;
        this.place_depature = place_depature;
        this.img = img;
        this.categories = categories;

    }

    public Product() {
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
