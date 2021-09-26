package com.flying.cart.Cart;

import java.util.ArrayList;
import java.util.List;

import com.flying.cart.Product.Product;
import com.flying.cart.User.Client;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "paymentLogs")
public class PaymentLog {
    @Id
    private String _id;
    private Double payment;
    @DBRef
    private Client client;
    @DBRef
    private List<Product> products = new ArrayList<>();

    public PaymentLog() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
