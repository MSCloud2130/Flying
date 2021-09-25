package com.flying.seeker.service;

import java.util.List;

import com.flying.seeker.Users.Client;
import com.flying.seeker.Users.ClientRepository;
import com.flying.seeker.Users.Seller;
import com.flying.seeker.Users.SellerRepository;
import com.flying.seeker.product.Product;
import com.flying.seeker.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FR {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/test")
    public List<Product> finadALl() {
        List<Product> ls = productRepository.findAll();
        return ls;
    }

    @GetMapping("/test2")
    public List<Seller> finadAL2l() {
        List<Seller> ls = sellerRepository.findAll();
        return ls;
    }

    @GetMapping("/test4")
    public List<Client> finadA2Ll() {
        List<Client> ls = clientRepository.findAll();
        return ls;
    }
}
