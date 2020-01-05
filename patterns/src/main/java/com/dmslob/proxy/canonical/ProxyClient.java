package com.dmslob.proxy.canonical;

public class ProxyClient {

    public static void main(String[] args) {
        Order order = new Order(5L);
        order.putItem("XP", 2);

        RealOrderService service = new RealOrderService();
        service.processOrder(order);

        PermissionCheckingOrderServiceProxy proxy = new PermissionCheckingOrderServiceProxy(service);
        proxy.setCurrentUser(5);
        proxy.processOrder(order);
        proxy.setCurrentUser(2);
        proxy.processOrder(order);
    }
}
