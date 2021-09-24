package com.flying.seeker.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.flying.seeker.ArrayOfProducts;
import com.flying.seeker.ArrayOfReviews;
import com.flying.seeker.ArrayOfString;
import com.flying.seeker.Product;
import com.flying.seeker.Review;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static final ArrayOfProducts products = new ArrayOfProducts();

    @PostConstruct
    public void init() throws DatatypeConfigurationException {
        // TODO: fill array
        ArrayOfReviews reviews = new ArrayOfReviews();
        ArrayOfString categories = new ArrayOfString();
        Review r = new Review();
        r.setComentary("Soy un comentario");
        r.setStars(5);
        Review r2 = new Review();
        r2.setComentary("Soy un comentario o no?");
        r2.setStars(4);
        reviews.getReview().add(r);
        reviews.getReview().add(r2);
        categories.getString().add("asd");
        categories.getString().add("saddsa");
        Product pr = new Product();
        pr.setCategories(categories);
        pr.setReviews(reviews);
        pr.setName("nom");
        pr.setPrice(18.0);

        Date fecha = new Date();
        Instant ins = Instant.now();
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(fecha.from(ins));
        XMLGregorianCalendar xCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        pr.setDate(xCalendar);

        pr.setDescription("ssssssss");
        pr.setIsOnOffer(true);
        pr.setPlaceArrival("BOG");
        pr.setPlaceDepature("MED");
        pr.setProductOwner("IDsad");
        pr.setImg("img");

        products.getProduct().add(pr);

    }

    public ArrayOfProducts getProducts(String name) {
        return products;
    }

}
