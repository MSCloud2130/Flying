package com.flying.auth;
import java.util.List;

import com.flying.auth.models.Client;
import com.flying.auth.models.ClientRepository;
import com.flying.auth.models.Seller;
import com.flying.auth.models.SellerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Auth {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SellerRepository sellerRepository;
    @PostMapping("/client/signup")
    public String signUp(@RequestBody Client client){
        String token = "";
        if(clientEmailExist(client.getEmail())) return "";
        RandomString string = new RandomString();
        token = string.nextString();
        client.setToken(token);
        clientRepository.insert(client);
        return token;
    }
    private boolean clientEmailExist(String email) {
        List<Client> clients = clientRepository.findAll();
        for(Client client : clients){
            if(client.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }
    @PostMapping("/client/signin")
    public String signIn(@RequestBody Client client){
        String token = "";
        client = getClient(client.getEmail(), client.getPassword());
        if(client == null) return "";
        RandomString string = new RandomString();
        token = string.nextString();
        client.setToken(token);
        clientRepository.save(client);
        return token;
    }
    private Client getClient(String email, String password) {
        List<Client> clients = clientRepository.findAll();
        for(Client client : clients){
            if(client.getEmail().equalsIgnoreCase(email) && client.getPassword().equalsIgnoreCase(password)) return client;
        }
        return null;
    }
    @PostMapping("/seller/signup")
    public String signUp(@RequestBody Seller seller){
        String token = "";
        if(sellerEmailExist(seller.getEmail())) return "";
        RandomString string = new RandomString();
        token = string.nextString();
        seller.setToken(token);
        sellerRepository.insert(seller);
        return token;
    }
    private boolean sellerEmailExist(String email) {
        List<Seller> sellers = sellerRepository.findAll();
        for(Seller seller : sellers){
            if(seller.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }
    @PostMapping("/seller/signin")
    public String signIn(@RequestBody Seller seller){
        String token = "";
        seller = getSeller(seller.getEmail(), seller.getPassword());
        if(seller == null) return "";
        RandomString string = new RandomString();
        token = string.nextString();
        seller.setToken(token);
        sellerRepository.save(seller);
        return token;
    }
    private Seller getSeller(String email, String password) {
        List<Seller> sellers = sellerRepository.findAll();
        for(Seller seller : sellers){
            if(seller.getEmail().equalsIgnoreCase(email) && seller.getPassword().equalsIgnoreCase(password)) return seller;
        }
        return null;
    }
}
