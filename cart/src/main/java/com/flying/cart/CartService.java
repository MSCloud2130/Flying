package com.flying.cart;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flying.cart.Cart.Cart;
import com.flying.cart.Cart.CartRepository;
import com.flying.cart.Cart.PaymentLog;
import com.flying.cart.Cart.PaymentLogRepository;
import com.flying.cart.Product.Product;
import com.flying.cart.Product.ProductRepository;
import com.flying.cart.User.Client;
import com.flying.cart.User.ClientRepository;
import com.flying.cart.User.Seller;
import com.flying.cart.User.SellerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    Logger logger = LoggerFactory.getLogger(CartService.class);
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PaymentLogRepository paymentLogRepository;

    @Autowired
    SellerRepository sellerRepository;

    public Product getProduct(String productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    public Client getClient(String token) {
        return clientRepository.findByToken(token);
    }

    public Seller getSeller(String id) {
        List<Seller> sellers = sellerRepository.findAll();
        for (Seller seller : sellers) {
            List<Product> products = seller.getMy_products();
            for (Product product : products) {
                if (product.get_id().equals(id))
                    return seller;
            }
        }
        return null;
    }

    public Cart getCart(String token) {
        Client client = clientRepository.findByToken(token);

        if (client.getCart().get_id() == null) {
            Cart cart = new Cart(0.0);
            cartRepository.insert(cart);
            client.setCart(cart);
            clientRepository.save(client);
        }

        return client.getCart();
    }

    public Cart addProduct(String token, String idProduct) {
        try {
            Cart cart = getCart(token);
            Product product = getProduct(idProduct);
            Boolean flag = false;
            if (cart.addProduct(product)) {
                cart.updateValue(product.getPrice());
                flag = true;
            }
            if (flag) {
                cartRepository.save(cart);
            }

            return cart;
        } catch (Exception e) {
            return null;
        }

    }

    public Cart removeProduct(String token, String idProduct) {
        try {

            Cart cart = getCart(token);
            Product product = getProduct(idProduct);
            cart.removeProduct(product);
            cartRepository.save(cart);
            return cart;
        } catch (Exception e) {
            logger.info(e.toString());
            return null;
        }
    }

    public List<PaymentLog> makePayment(String token) {
        try {
            Client client = getClient(token);
            Cart cart = getCart(token);
            List<Product> products = cart.getProductos();
            Double price = cart.getValue();
            List<PaymentLog> payments = new ArrayList<>();

            if (price == 0)
                return null;
            if (!client.isThereEnoughMoney(price))
                return null;

            for (Product product : products) {
                PaymentLog payment = new PaymentLog();
                Seller seller = getSeller(product.get_id());
                if (seller != null) {
                    payment.setBuyer_id(client.get_id());
                    payment.setProduct_id(product.get_id());
                    payment.setSeller_id(seller.get_id());
                    Date date = new Date();
                    Instant instant = Instant.now();
                    payment.setDate(date.from(instant));
                    client.addProduct(product);
                    payments.add(payment);
                    paymentLogRepository.save(payment);
                }
            }
            cart.clearCart();
            cartRepository.save(cart);
            return payments;
        } catch (Exception e) {
            logger.info(e.toString());
            return null;
        }

    }

}
