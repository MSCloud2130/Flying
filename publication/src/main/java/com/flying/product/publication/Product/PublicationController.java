package com.flying.product.publication.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.flying.product.publication.Review.Review;
import com.flying.product.publication.Review.ReviewRepository;
import com.flying.product.publication.User.Client;
import com.flying.product.publication.User.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/products")
public class PublicationController{
    
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/product/{id}")
    public Product getIndividualPublication(@PathVariable("id") String id) {
        Optional<Product> auxProduct = productRepository.findById(id);
        if (auxProduct.isPresent()) {
            return auxProduct.get();
        }
        return null;
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable("id") String id, @RequestHeader("question") String question, @RequestHeader("token") String token) {
        Client client = clientRepository.findByToken(token);
        if (client != null) {
            Product product = getIndividualPublication(id);
            List<Review> reviewList = product.getReview();
            Review rev = new Review();
            rev.setComentary(question);
            rev.setStars(0);
            reviewRepository.insert(rev);
            reviewList.add(rev);
            product.setReview(reviewList);
            productRepository.delete(product);
            productRepository.insert(product);
            return new ResponseEntity<String>("Accepted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/product/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestBody List<String> category, @RequestHeader("atribute") String atribute) {
        if (atribute.equals("transport")) {
            return new ResponseEntity<List<Product>>(filterByTransport(category), HttpStatus.ACCEPTED); 
        }
        else if (atribute.equals("accommodation")) {
            return new ResponseEntity<List<Product>>(filterByAccomodation(category), HttpStatus.ACCEPTED); 
        }
        else if (atribute.equals("food_service")) {
            return new ResponseEntity<List<Product>>(filterByFoodService(category), HttpStatus.ACCEPTED); 
        }
        else if (atribute.equals("category")) {
            return new ResponseEntity<List<Product>>(filterByCategories(category), HttpStatus.ACCEPTED); 
        }
        
        return new ResponseEntity<List<Product>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public List<Product> filterByCategories(List<String> filter) {
        return productRepository.findAll().stream()
                                .filter(x -> x.getCategories().stream().anyMatch(filter::contains))
                                .collect(Collectors.toList());
    }

    public List<Product> filterByTransport(List<String> filter) {
        return productRepository.findAll().stream()
                                .filter(x -> filter.contains(x.getTransport()))
                                .collect(Collectors.toList());
    }

    public List<Product> filterByAccomodation(List<String> filter) {
        return productRepository.findAll().stream()
                                .filter(x -> filter.contains(x.getAccommodation()))
                                .collect(Collectors.toList());
    }

    public List<Product> filterByFoodService(List<String> filter) {
        return productRepository.findAll().stream()
                                .filter(x -> filter.contains(x.getFood_service()))
                                .collect(Collectors.toList());
    }
}
