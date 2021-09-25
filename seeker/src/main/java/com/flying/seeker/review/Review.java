package com.flying.seeker.review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Reviews")
public class Review {

    @Id
    private String _id;
    private String product_id;
    private String owner_id;
    private String comentary;
    private Integer stars;

    public Review(String product_id, String owner_id, String comentary, Integer stars) {
        this.product_id = product_id;
        this.owner_id = owner_id;
        this.comentary = comentary;
        this.stars = stars;
    }

    public Review() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getComentary() {
        return comentary;
    }

    public void setComentary(String comentary) {
        this.comentary = comentary;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

}
