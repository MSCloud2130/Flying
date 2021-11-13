package com.flying.store;

import java.util.List;

import com.flying.store.models.Client;
import com.flying.store.models.Product;
import com.flying.store.models.Review;
import com.flying.store.models.Seller;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StoreController {
    HttpHeaders headers = new HttpHeaders();
    Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.GET, produces = "application/json")
    public String searchProduct(@RequestParam String searchString) {
        String response = restTemplate.getForObject("http://seekerMiddleware/search?searchString={searchString}",
                String.class, searchString);
        return response;

    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCart(@RequestHeader String token) {
        try {
            headers.set("token", token);
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange("http://cart/cart", HttpMethod.GET, httpEntity,
                    String.class);
            if (response.getBody() == null)
                return new ResponseEntity<>(null, null, HttpStatus.SC_NOT_FOUND);
            return new ResponseEntity<>(response.getBody(), null, HttpStatus.SC_OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cart/{productId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> addProductCart(@RequestHeader("token") String token, @PathVariable String productId) {
        try {
            headers.set("token", token);
            HttpEntity httpEntity = new HttpEntity(headers);
            String uri = "http://cart//cart/" + productId;
            logger.info(uri);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
            if (response.getBody() == null)
                return new ResponseEntity<>(null, null, HttpStatus.SC_NOT_FOUND);
            return new ResponseEntity<>(response.getBody(), null, HttpStatus.SC_OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cart", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> removeProductCart(@RequestHeader("token") String token,
            @RequestParam String removeProductId) {
        try {
            headers.set("token", token);
            HttpEntity httpEntity = new HttpEntity(headers);
            String uri = "http://cart//cart?removeProductId={removeProductId}";
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class,
                    removeProductId);
            if (response.getBody() == null)
                return new ResponseEntity<>(null, null, HttpStatus.SC_NOT_FOUND);
            return new ResponseEntity<>(response.getBody(), null, HttpStatus.SC_OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cart/payment", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> makePayment(@RequestHeader("token") String token) {
        try {
            headers.set("token", token);
            HttpEntity httpEntity = new HttpEntity(headers);
            String uri = "http://cart//cart/payment";
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
            if (response.getBody() == null)
                return new ResponseEntity<>(null, null, HttpStatus.SC_NOT_FOUND);
            return new ResponseEntity<>(response.getBody(), null, HttpStatus.SC_OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/client/signin", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity<String> clientSignin(@RequestBody Client client) {
        String uri = "http://auth//auth/client/signin";
        ResponseEntity<String> response = restTemplate.postForEntity(uri, client, String.class);
        return response;
    }

    @RequestMapping(value = "/client/signup", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity<String> clientSignup(@RequestBody Client client) {
        String uri = "http://auth//auth/client/signup";
        ResponseEntity<String> response = restTemplate.postForEntity(uri, client, String.class);
        return response;
    }

    @RequestMapping(value = "/seller/signin", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity<String> sellerSignin(@RequestBody Seller seller) {
        String uri = "http://auth//auth/seller/signin";
        ResponseEntity<String> response = restTemplate.postForEntity(uri, seller, String.class);
        return response;
    }

    @RequestMapping(value = "/seller/signup", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity<String> sellerSignup(@RequestBody Seller seller) {
        String uri = "http://auth//auth/seller/signup";
        ResponseEntity<String> response = restTemplate.postForEntity(uri, seller, String.class);
        return response;
    }

    @RequestMapping(value = "/products/product/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getIndividualPublication(@PathVariable("id") String id) {
        String uri = "http://publication//products/product/" + id;
        return restTemplate.getForObject(uri, String.class);
    }

    // hacer comentario
    @RequestMapping(value = "/products/product/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> getIndividualPublication(@PathVariable("id") String id,
            @RequestHeader("question") String question, @RequestHeader("token") String token) {
        String uri = "http://publication//products/product/" + id;
        headers.set("token", token);
        headers.set("question", question);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
    }

    @RequestMapping(value = "/products/category", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getProductsByCategory(@RequestBody List<String> category,
            @RequestHeader("atribute") String atribute) {
        headers.set("atribute", atribute);
        String uri = "http://publication//products/product/category";
        HttpEntity<List<String>> entity = new HttpEntity<>(category, headers);
        return restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
    }

    @RequestMapping(value = "/products/product", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @RequestHeader("token") String token) {
        headers.set("token", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> newProduct = new HttpEntity<>(product, headers);
        String uri = "http://publication/products";
        ResponseEntity<Product> response = restTemplate.postForEntity(uri, newProduct, Product.class);
        return response;
    }

    @RequestMapping(value = "/products/product/review", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Review> createReview(@RequestParam String product_id, @RequestHeader("token") String token,
            @RequestBody Review review) {
        headers.set("token", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Review> newProduct = new HttpEntity<>(review, headers);
        String uri = "http://publication/products/review?product_id=" + product_id;
        ResponseEntity<Review> response = restTemplate.postForEntity(uri, newProduct, Review.class);
        return response;
    }

    @RequestMapping(value = "/products/product/country-info", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getCountryInfo(@RequestParam String product_id) {
        String uri = "http://publication/products/country-info?product_id=" + product_id;
        return restTemplate.getForEntity(uri, Object.class);
    }

    @RequestMapping(value = "/products/product/weather", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getWeatherInfo(@RequestParam String product_id) {
        String uri = "http://publication/products/weather?product_id=" + product_id;
        return restTemplate.getForEntity(uri, Object.class);
    }

    @RequestMapping(value = "/products/product/map", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getMapInfo(@RequestParam String product_id) {
        String uri = "http://publication/products/map?product_id=" + product_id;
        return restTemplate.getForEntity(uri, Object.class);
    }
}
