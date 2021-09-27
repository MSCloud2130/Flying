package com.flying.cart.Cart;

import java.util.ArrayList;
import java.util.List;

import com.flying.cart.Product.Product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carts")
public class Cart {

    @Id
    private String _id;
    private Double value;
    @DBRef
    private List<Product> productos = new ArrayList<>();

    public Cart() {
    }

    public Cart(Double value) {
        this.value = value;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<Product> getProductos() {
        return productos;
    }

    public void setProductos(List<Product> productos) {
        this.productos = productos;
    }

    public boolean addProduct(Product product) {
        for (Product product2 : productos) {
            if (product2.get_id().equals(product.get_id())) {
                return false;
            }
        }
        this.productos.add(product);
        return true;
    }

    public void removeProduct(Product product) {
        for (Product product2 : productos) {
            if (product2.get_id().equals(product.get_id())) {
                this.productos.remove(product2);
                updateValueRemove(product.getPrice());
                return;
            }
        }
    }

    public void updateValue(Double value) {
        this.value += value;
    }

    public void updateValueRemove(Double value) {
        this.value = this.value - value;
    }

    public void clearCart() {
        this.productos.clear();
        this.value = 0.0;
    }

}
