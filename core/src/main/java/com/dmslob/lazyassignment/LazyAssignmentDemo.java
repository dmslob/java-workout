package com.dmslob.lazyassignment;

import java.util.function.Supplier;

public class LazyAssignmentDemo {

    public static void main(String[] args) {

        LazyVar lazyString = LazyVar.let(() -> "Lazy String");
        System.out.println(lazyString.get());
    }
}

class LazyVar<T> implements Supplier {

    private final Supplier supplier;
    private boolean supplied = false;
    private T value;

    private LazyVar(Supplier supplier) {
        this.supplier = supplier;
    }

    public static LazyVar let(Supplier supplier) {
        return new LazyVar(supplier);
    }

    @Override
    public T get() {
        if (supplied) {
            return value;
        }
        supplied = true;
        return value = (T) supplier.get();
    }
}