package com.flying.seeker.review;

import com.flying.seeker.product.Product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import ch.qos.logback.core.net.server.Client;
import lombok.Data;

@Data
@Document()
public class Review {

    @Id
    private String id;
    private Product product;
    private Client owner;
    private String comentary;
    private Integer stars;

    public Review(Product product, Client owner, String comentary, Integer stars) {
        this.product = product;
        this.owner = owner;
        this.comentary = comentary;
        this.stars = stars;
    }

    public Review() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
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
