package com.flying.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StoreController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.GET, produces = "application/json")
    public String searchProduct(@RequestParam String searchString) {
        String response = restTemplate.getForObject("http://seekerClient/search?searchString={searchString}",
                String.class, searchString);
        return response;

    }
}
