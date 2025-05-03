package com.dmslob.functional.functors;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.function.Function;

public class FList<T> implements Functor<T, FList<?>> {
    private final ImmutableList<T> list;

    FList(Iterable<T> value) {
        this.list = ImmutableList.copyOf(value);
    }

    @Override
    public <R> FList<?> map(Function<T, R> f) {
        ArrayList<R> result = new ArrayList<>(list.size());
        for (T t : list) {
            result.add(f.apply(t));
        }
        return new FList<>(result);
    }
}
