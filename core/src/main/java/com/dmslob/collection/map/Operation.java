package com.dmslob.collection.map;

import java.math.BigDecimal;
import java.util.Objects;

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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Operation)) return false;
//        Operation operation = (Operation) o;
//        return acc.equals(operation.acc);
//    }

    @Override
    public int hashCode() {
        return 1;
    }
}
