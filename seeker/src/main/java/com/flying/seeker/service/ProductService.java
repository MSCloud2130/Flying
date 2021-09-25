package com.flying.seeker.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.flying.seeker.gen.*;
import com.flying.seeker.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ArrayOfProducts getProducts(String searchString) {
        List<com.flying.seeker.product.Product> productsDB = productRepository.findAll();
        ArrayOfProducts filter_products = new ArrayOfProducts();
        productsDB.forEach(product -> {
            if (product.getName().contains(searchString) || product.getDescription().contains(searchString)) {
                Product productCopy = new Product();
                productCopy.setId(product.get_id());
                productCopy.setName(product.getName());
                productCopy.setPrice(product.getPrice());
                GregorianCalendar gCalendar = new GregorianCalendar();
                gCalendar.setTime(product.getDate());
                XMLGregorianCalendar xCalendar;
                try {
                    xCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
                    productCopy.setDate(xCalendar);
                } catch (DatatypeConfigurationException e) {
                    e.printStackTrace();
                }
                productCopy.setDescription(product.getDescription());
                productCopy.setIsOnOffer(product.getIsOnOffer());
                productCopy.setPlaceArrival(product.getPlace_arrival());
                productCopy.setImg(product.getImg());
                ArrayOfString arrayOfString = new ArrayOfString();
                product.getCategories().forEach(category -> {
                    arrayOfString.getString().add(category);
                });
                productCopy.setCategories(arrayOfString);
                ArrayOfReviews arrayOfReviews = new ArrayOfReviews();
                product.getReview().forEach(review -> {
                    Review r = new Review();
                    r.setId(review.get_id());
                    r.setComentary(review.getComentary());
                    r.setStars(review.getStars());
                    arrayOfReviews.getReview().add(r);
                });
                productCopy.setReviews(arrayOfReviews);
                filter_products.getProduct().add(productCopy);
            }
        });
        return filter_products;
    }

}
