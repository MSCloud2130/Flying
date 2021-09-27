package com.flying.store;

import java.util.List;

import com.flying.store.models.Client;
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

    @RequestMapping(value = "/auth/client/signin", method = RequestMethod.POST, produces = "text/plain")
    public String clientSignin(@RequestBody Client client) {
        String uri = "http://auth//auth/client/signin";
        String response = restTemplate.postForObject(uri, HttpMethod.POST, String.class);
        return response;
    }
    @RequestMapping(value = "/auth/client/signup", method = RequestMethod.POST, produces = "text/plain")
    public String clientSignup(@RequestBody Client client) {
        String uri = "http://auth//auth/client/signup";
        String response = restTemplate.postForObject(uri, HttpMethod.POST, String.class);
        return response;
    }
    @RequestMapping(value = "/auth/seller/signin", method = RequestMethod.POST, produces = "text/plain")
    public String sellerSignin(@RequestBody Seller seller) {
        String uri = "http://auth//auth/seller/signin";
        String response = restTemplate.postForObject(uri, HttpMethod.POST, String.class);
        return response;
    }
    @RequestMapping(value = "/auth/seller/signup", method = RequestMethod.POST, produces = "text/plain")
    public String sellerSignup(@RequestBody Seller seller) {
        String uri = "http://auth//auth/seller/signup";
        String response = restTemplate.postForObject(uri, HttpMethod.POST, String.class);
        return response;
    }

    @RequestMapping(value = "/publications/products/product/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getIndividualPublication(@PathVariable("id") String id) {
        String uri = "http://publication//products/product/" + id;
        return restTemplate.getForObject(uri, String.class);
    }
    
    @RequestMapping(value = "/publications/products/product/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> getIndividualPublication(@PathVariable("id") String id, @RequestHeader("question") String question, @RequestHeader("token") String token) {
        String uri = "http://publication//products/product/" + id;
        headers.set("token", token);
        headers.set("question", question);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
    }

    @RequestMapping(value = "/publications/publication/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getProductsByCategory(@RequestBody List<String> category, @RequestHeader("atribute") String atribute) {
        headers.set("atribute", atribute);
        String uri = "http://publication//products/category/";
        HttpEntity<List<String>> entity = new HttpEntity<>(category, headers);
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
    }
}
