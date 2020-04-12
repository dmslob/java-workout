package com.dmslob.collection.map;

import java.math.BigDecimal;

public class Operation {
    private final String acc;
    private final BigDecimal amount;

    public Operation(String acc, BigDecimal amount) {
        this.acc = acc;
        this.amount = amount;
    }

    public String getAcc() {
        return acc;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
