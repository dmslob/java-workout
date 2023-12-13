package com.dmslob.cas;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class PowerOfTwo {
    private final AtomicReference<BigInteger> current =
            new AtomicReference<>(null);

    BigInteger next() {
        BigInteger recent, next;
        do {
            recent = current.get();
            next = (recent == null) ? BigInteger.valueOf(1) : recent.shiftLeft(1);
        }
        while (!current.compareAndSet(recent, next));
        return next;
    }
}
