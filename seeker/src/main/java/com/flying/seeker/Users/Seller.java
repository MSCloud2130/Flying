package com.flying.seeker.Users;

import java.util.ArrayList;
import java.util.List;

import com.flying.seeker.product.Product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Seller {
    @Id
    private String _id;

    private String email;
    private String password;
    private String token;
    private String img;
    private List<Product> my_products = new ArrayList<>();

    public Seller(String email, String password, String token, String img, List<Product> my_products) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.img = img;
        this.my_products = my_products;
    }

}
