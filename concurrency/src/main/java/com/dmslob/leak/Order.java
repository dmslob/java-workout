package com.dmslob.leak;

import java.util.Arrays;

/**
 * Created by Dmytro_Slobodenyuk on 7/2/2018.
 */
public class Order {
    private final int id;

    private final String code;

    private final int amount;

    private final double price;

    private final long time;

    private final long[] padding;

    public Order(int id, String code, int amount, double price, long time) {
        super();
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.price = price;
        this.time = time;
        // This just makes the Order object bigger so that
        // the core runs out of heap more quickly.
        this.padding = new long[3000];
        Arrays.fill(padding, 0, padding.length - 1, -2);
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public long getTime() {
        return time;
    }
}
