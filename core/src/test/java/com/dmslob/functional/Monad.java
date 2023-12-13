package com.dmslob.functional;

import java.util.function.Function;

public class Monad<T> {
    final T value;

    private Monad(T value) {
        this.value = value;
    }

    public static <T> Monad<T> from(T value) {
        return new Monad<>(value);
    }

    public <U> Monad<U> flatMap(Function<T, Monad<U>> func) {
        return func.apply(value);
    }

    public <U> Monad<U> map(Function<T, U> func) {
        return flatMap(val -> new Monad<>(func.apply(val)));
    }
}
