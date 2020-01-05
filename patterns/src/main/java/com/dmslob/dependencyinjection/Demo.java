package com.dmslob.dependencyinjection;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class Demo {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentServiceFactory().getPaymentService(Payment.CASH);

        Cart cart = new Cart(paymentService);
        cart.addProduct(new Product("beer", new BigDecimal(56.35).setScale(2, RoundingMode.HALF_UP)));
        cart.buy();
    }
}
