package com.dmslob.collections.map;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation operation)) return false;
        return acc.equals(operation.acc);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
