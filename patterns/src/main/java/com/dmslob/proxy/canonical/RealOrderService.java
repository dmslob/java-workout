package com.dmslob.proxy.canonical;

public class RealOrderService implements OrderService {
    public void processOrder(Order order) {
        System.out.println("Order processed: " + order);
    }
}
