package com.flying.cart;

import java.util.List;

import com.flying.cart.Cart.Cart;
import com.flying.cart.Cart.PaymentLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> getCart(@RequestHeader("token") String token) {
        try {
            Cart cart = cartService.getCart(token);
            return new ResponseEntity<>(cart, null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
    }

    @PutMapping("/cart/{productId}")
    public ResponseEntity<Cart> addProduct(@RequestHeader("token") String token, @PathVariable String productId) {
        try {
            Cart cart = cartService.addProduct(token, productId);
            if (cart == null)
                return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(cart, null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cart")
    public ResponseEntity<Cart> removeProduct(@RequestHeader("token") String token,
            @RequestParam String removeProductId) {
        try {
            Cart cart = cartService.removeProduct(token, removeProductId);
            if (cart == null)
                return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(cart, null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cart/payment")
    public ResponseEntity<List<PaymentLog>> makePayment(@RequestHeader("token") String token) {
        try {
            List<PaymentLog> payments = cartService.makePayment(token);
            if (payments == null)
                return new ResponseEntity<>(null, null, HttpStatus.NOT_MODIFIED);

            return new ResponseEntity<>(payments, null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
