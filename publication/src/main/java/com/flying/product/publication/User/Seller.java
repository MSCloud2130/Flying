package com.flying.product.publication.User;

import java.util.ArrayList;
import java.util.List;

import com.flying.product.publication.Product.Product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Sellers")
public class Seller {
    @Id
    private String _id;

    private String name;
    private Integer age;
    private String phone;
    private String social;
    private String email;
    private String password;
    private String token;
    private String img;
    private Double wallet;
    @DBRef
    private List<Product> my_products = new ArrayList<>();

    public Seller() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
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