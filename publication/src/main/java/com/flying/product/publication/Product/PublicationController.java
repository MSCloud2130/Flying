package com.flying.product.publication.Product;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.flying.product.publication.Review.Review;
import com.flying.product.publication.Review.ReviewRepository;
import com.flying.product.publication.User.Client;
import com.flying.product.publication.User.ClientRepository;
import com.flying.product.publication.User.Seller;
import com.flying.product.publication.User.SellerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/products")
public class PublicationController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/product/{id}")
    public Product getIndividualPublication(@PathVariable("id") String id) {
        Optional<Product> auxProduct = productRepository.findById(id);
        if (auxProduct.isPresent()) {
            return auxProduct.get();
        }
        return null;
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable("id") String id,
            @RequestHeader("question") String question, @RequestHeader("token") String token) {
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

    @PostMapping("/product/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestBody List<String> category,
            @RequestHeader("atribute") String atribute) {
        if (atribute.equals("transport")) {
            return new ResponseEntity<List<Product>>(filterByTransport(category), HttpStatus.ACCEPTED);
        } else if (atribute.equals("accommodation")) {
            return new ResponseEntity<List<Product>>(filterByAccomodation(category), HttpStatus.ACCEPTED);
        } else if (atribute.equals("food_service")) {
            return new ResponseEntity<List<Product>>(filterByFoodService(category), HttpStatus.ACCEPTED);
        } else if (atribute.equals("category")) {
            return new ResponseEntity<List<Product>>(filterByCategories(category), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<List<Product>>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestHeader("token") String token,
            @RequestBody Product newProduct) {

        Seller seller = sellerRepository.findByToken(token);

        if (seller == null)
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);

        if (newProduct.getName() == null || newProduct.getCategory() == null || newProduct.getImg() == null
                || newProduct.getIsOnOffer() == null || newProduct.getDescription() == null
                || newProduct.getPrice() == null || newProduct.getCountry() == null)
            return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);

        if (newProduct.getCategory().toLowerCase().equals("transport")) {
            if (newProduct.getDate_arrival() == null || newProduct.getDate_depature() == null
                    || newProduct.getTransport() == null || newProduct.getPlace_arrival() == null
                    || newProduct.getPlace_depature() == null || newProduct.getAccommodation() != null
                    || newProduct.getFood_service() != null || newProduct.getGuide() != null)
                return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);

        } else if (newProduct.getCategory().toLowerCase().equals("food")) {
            if (newProduct.getDate_arrival() != null || newProduct.getDate_depature() != null
                    || newProduct.getTransport() != null || newProduct.getPlace_arrival() != null
                    || newProduct.getPlace_depature() != null || newProduct.getAccommodation() != null
                    || newProduct.getFood_service() == null || newProduct.getGuide() != null)
                return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);

        } else if (newProduct.getCategory().toLowerCase().equals("ecotrip")) {
            if (newProduct.getDate_arrival() == null || newProduct.getDate_depature() == null
                    || newProduct.getTransport() != null || newProduct.getPlace_arrival() == null
                    || newProduct.getPlace_depature() != null || newProduct.getAccommodation() != null
                    || newProduct.getFood_service() != null || newProduct.getGuide() == null)
                return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);

        } else if (newProduct.getCategory().toLowerCase().equals("lodging")) {
            if (newProduct.getDate_arrival() == null || newProduct.getDate_depature() == null
                    || newProduct.getTransport() != null || newProduct.getPlace_arrival() == null
                    || newProduct.getPlace_depature() != null || newProduct.getAccommodation() == null
                    || newProduct.getFood_service() != null || newProduct.getGuide() != null)
                return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);

        } else
            return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);

        productRepository.save(newProduct);
        seller.addProduct(newProduct);
        sellerRepository.save(seller);
        return new ResponseEntity<>(newProduct, null, HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestParam String product_id, @RequestHeader("token") String token,
            @RequestBody Review review) {
        Client client = clientRepository.findByToken(token);
        Product product = productRepository.findById(product_id).orElseThrow();
        if (client == null || product == null || review.getStars() == null || review.getComentary() == null)
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);

        if (review.getStars() < 1 || review.getStars() > 5)
            return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);

        client.getMy_reviews().add(review);
        product.getReview().add(review);
        reviewRepository.save(review);
        clientRepository.save(client);
        productRepository.save(product);

        return new ResponseEntity<>(review, null, HttpStatus.OK);
    }

    @GetMapping("/country-info")
    public Object getCountry(@RequestParam String product_id) {
        Product product = productRepository.findById(product_id).orElseThrow();
        String url = "";
        url = "https://restcountries.com/v3.1/name/" + product.getCountry();
        ResponseEntity<Object> request = restTemplate
                .getForEntity("https://restcountries.com/v3.1/name/" + product.getCountry(), Object.class);
        return request.getBody();
    }

    @GetMapping("/weather")
    public Object getMap5(@RequestParam String product_id) {
        Product product = productRepository.findById(product_id).orElseThrow();
        String location = product.getPlace_arrival();
        long days = Math.abs(TimeUnit.DAYS.convert(
                product.getDate_arrival().getTime() - product.getDate_depature().getTime(), TimeUnit.MILLISECONDS));

        ResponseEntity<Object> request = restTemplate.getForEntity("https://api.weatherbit.io/v2.0/forecast/daily?city="
                + location + "&days=" + days + "&key=da7d8df4d04a4a3a9548bb3da5b4ebd0", Object.class);
        return request.getBody();
    }

    @GetMapping("/map")
    public Object getMap(@RequestParam String product_id) {
        Product product = productRepository.findById(product_id).orElseThrow();
        if (product.getCategory().toLowerCase().equals("lodging")) {
            String location = product.getPlace_arrival();
            ResponseEntity<Object> request = restTemplate
                    .getForEntity("https://maps.googleapis.com/maps/api/geocode/json?address=" + location
                            + "&key=AIzaSyAFN7AjQZkE9PhvfNwXnQzgr-hv9cghKKU", Object.class);
            return request.getBody();
        } else if (product.getCategory().toLowerCase().equals("transport")) {
            String origin = product.getPlace_depature();
            String des = product.getPlace_arrival();
            ResponseEntity<Object> request = restTemplate
                    .getForEntity("https://maps.googleapis.com/maps/api/directions/json?origin=" + origin
                            + "&destination=" + des + "&key=AIzaSyAFN7AjQZkE9PhvfNwXnQzgr-hv9cghKKU", Object.class);
            return request.getBody();
        }
        return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> filterByCategories(List<String> filter) {
        return productRepository.findAll().stream().filter(x -> filter.contains(x)).collect(Collectors.toList());
    }

    public List<Product> filterByTransport(List<String> filter) {
        return productRepository.findAll().stream().filter(x -> filter.contains(x.getTransport()))
                .collect(Collectors.toList());
    }

    public List<Product> filterByAccomodation(List<String> filter) {
        return productRepository.findAll().stream().filter(x -> filter.contains(x.getAccommodation()))
                .collect(Collectors.toList());
    }

    public List<Product> filterByFoodService(List<String> filter) {
        return productRepository.findAll().stream().filter(x -> filter.contains(x.getFood_service()))
                .collect(Collectors.toList());
    }
}