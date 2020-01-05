package com.dmslob.dependencyinjection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class Cart {

    private List<Product> products = new ArrayList<Product>();
    private PaymentService paymentService;

    public Cart(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void buy() {
        products.stream().forEach(paymentService::pay);
    }
}
