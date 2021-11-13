package com.flying.product.publication.User;

import java.util.ArrayList;
import java.util.List;

import com.flying.product.publication.Product.Product;
import com.flying.product.publication.Review.Review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Clients")
public class Client {

    @Id
    private String _id;
    private String name;
    private Integer age;
    private String email;
    private String img;
    private String password;
    private String token;
    private Double wallet;
    @DBRef
    private List<Product> purchased_product = new ArrayList<>();
    @DBRef
    private List<Review> my_reviews = new ArrayList<>();
    /*
     * @DBRef private Cart cart = new Cart();
     */

    public Client() {
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Review> getMy_reviews() {
        return my_reviews;
    }

    public void setMy_reviews(List<Review> my_reviews) {
        this.my_reviews = my_reviews;
    }

    public List<Product> getPurchased_product() {
        return purchased_product;
    }

    public void setPurchased_product(List<Product> purchased_product) {
        this.purchased_product = purchased_product;
    }

}
