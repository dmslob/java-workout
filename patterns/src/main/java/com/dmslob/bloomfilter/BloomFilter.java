package com.dmslob.bloomfilter;

import java.util.BitSet;
import java.util.List;
import java.util.function.Function;

public class BloomFilter<T> {
    private final BitSet bitSet;
    private final int n;
    private final List<Function<T, Integer>> hashFunctions;

    public BloomFilter(final int n, final List<Function<T, Integer>> hashFunctions) {
        this.n = n;
        this.bitSet = new BitSet(n);
        this.hashFunctions = hashFunctions;
    }

    public void add(T value) {
        for (Function<T, Integer> function : hashFunctions) {
            int hash = mapHashToInt(function.apply(value));
            bitSet.set(hash, true);
        }
    }

    public boolean mightContain(T value) {
        for (Function<T, Integer> function : hashFunctions) {
            int hash = mapHashToInt(function.apply(value));
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }

    private int mapHashToInt(int hash) {
        return hash & (n - 1);
    }
}
