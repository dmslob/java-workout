package com.dmslob.proxy;

import com.dmslob.proxy.canonical.Order;

public class PermissionChecker {

    private final ThreadLocal<Long> currentUser = new ThreadLocal<>();

    public void setCurrentUser(long userId) {
        currentUser.set(userId);
    }

    public void checkPermission(Order order) {
        if (order.getUserId() != currentUser.get()) {
            throw new IllegalStateException("Order for another user can't be processed: " + order);
        }
    }
}
