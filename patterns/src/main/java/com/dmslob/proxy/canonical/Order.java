package com.dmslob.proxy.canonical;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private final long userId;
    private final Map<String, Integer> items = new HashMap<>();

    public Order(long userId) {
        this.userId = userId;
    }

    public void putItem(String code, int count) {
        items.put(code, count);
    }

    public long getUserId() {
        return userId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{userId=" + userId + ", items=" + items + "}";
    }
}
