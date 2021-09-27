package com.flying.store.models;

public class Review {

    private String _id;
    private String comentary;
    private Integer stars;

    public Review(String comentary, Integer stars) {
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
