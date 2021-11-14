package com.flying.seekerClient;

import com.flying.seekerClient.gen.ArrayOfProducts;
import com.flying.seekerClient.gen.GetProductsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductSeekerMiddleware {

    @Autowired
    ProductClient productClient;

    @GetMapping("/search")
    public ArrayOfProducts searchProducts(@RequestParam String searchString) {
        GetProductsResponse response = productClient.getProducts(searchString);
        return response.getProducts();
    }
}
