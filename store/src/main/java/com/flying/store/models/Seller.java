package com.flying.store.models;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private String _id;
    private String name;
    private String email;
    private String password;
    private String token;
    private String img;
    private List<Product> my_products = new ArrayList<>();

    public Seller() {
    }

    public Seller(String name, String email, String password, String token, String img) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.img = img;
    }

    public List<Product> getMy_products() {
        return my_products;
    }

    public void addProduct(Product p) {
        this.my_products.add(p);
    }

    public void setMy_products(List<Product> my_products) {
        this.my_products = my_products;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}