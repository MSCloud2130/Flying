package com.flying.cart.Cart;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentLogRepository extends MongoRepository<PaymentLog, String> {

}
