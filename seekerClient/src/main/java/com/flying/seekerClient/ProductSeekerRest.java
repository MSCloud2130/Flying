package com.flying.seekerClient;

import com.flying.seekerClient.gen.GetProductsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductSeekerRest {

    @Autowired
    ProductClient productClient;

    @GetMapping("/test")
    public GetProductsResponse searchProducts() {
        GetProductsResponse response = productClient.getProducts("dsa");
        return response;
    }
}
