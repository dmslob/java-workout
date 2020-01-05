package com.dmslob.proxy.canonical;

public class PermissionCheckingOrderServiceProxy implements OrderService {

    private final ThreadLocal<Long> currentUser = new ThreadLocal<>();
    private final OrderService delegate;

    public PermissionCheckingOrderServiceProxy(OrderService delegate) {
        this.delegate = delegate;
    }

    public void setCurrentUser(long userId) {
        currentUser.set(userId);
    }

    public void processOrder(Order order) {
        if (order.getUserId() != currentUser.get()) {
            throw new IllegalStateException("Order for another user can't be processed: " + order);
        }
        delegate.processOrder(order);
    }
}
