package com.dmslob;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class AsyncTest {

    @Test
    public void foo() {
        Identity<String> idString = new Identity<>("abc");
        Identity<Integer> idInt = idString.map(String::length);

        Customer customer = new Customer(new Address("Street 1"));
        Identity<byte[]> idBytes = new Identity<>(customer)
                .map(Customer::address)
                .map(Address::street)
                .map((String s) -> s.substring(0, 3))
                .map(String::toLowerCase)
                .map(String::getBytes);

    }
}

record Customer(Address address) {}
record Address(String street) {}

interface Functor<T, F extends Functor<?, ?>> {
    <R> F map(Function<T, R> f);
}

class Identity<T> implements Functor<T, Identity<?>> {
    private final T value;

    Identity(T value) {
        this.value = value;
    }

    public <R> Identity<R> map(Function<T, R> f) {
        final R result = f.apply(value);
        return new Identity<>(result);
    }
}

class FOptional<T> implements Functor<T, FOptional<?>> {

    private final T valueOrNull;

    private FOptional(T valueOrNull) {
        this.valueOrNull = valueOrNull;
    }

    public <R> FOptional<R> map(Function<T, R> f) {
        if (valueOrNull == null) {
            return empty();
        } else {
            return of(f.apply(valueOrNull));
        }
    }

    public static <T> FOptional<T> of(T a) {
        return new FOptional<T>(a);
    }

    public static <T> FOptional<T> empty() {
        return new FOptional<T>(null);
    }
}

//https://nurkiewicz.com/2016/06/functor-and-monad-examples-in-plain-java.html
class FList<T> implements Functor<T, FList<?>> {
    private final List<T> list;

    FList(Collection<T> value) {
        this.list = List.copyOf(value);
    }

    @Override
    public <R> FList<?> map(Function<T, R> f) {
        ArrayList<R> result = new ArrayList<R>(list.size());
        for (T t : list) {
            result.add(f.apply(t));
        }
        return new FList<>(result);
    }
}
