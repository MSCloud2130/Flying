package com.flying.store;

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
}
