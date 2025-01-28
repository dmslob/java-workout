package com.dmslob.generics.recursivebound;

import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * In generics, when a reference type has a type parameter that is bounded by the reference type itself,
 * then that type parameter is said to have a recursive type bound.
 * Example:
 * <T extends Comparable<T>>
 * It ensures that you can only compare objects of type T.
 * Without the type bound, Comparable compares any two Objects.
 * With the type bound, the compiler can ensure that only two objects of type T are compared.
 */
public class RecursiveBoundTest {

    public <T extends Comparable<T>> T max(List<T> list) {
        return list.stream()
                .max(Comparable::compareTo).get();
    }

    @Test
    public void should_define_max_size_of_fruit() {
        // given
        var apples = List.of(
                new Apple(2),
                new Apple(4),
                new Apple(6));

        var oranges = List.of(
                new Orange(2),
                new Orange(4),
                new Orange(8));
        // when
        Apple maxApple = max(apples);
        Orange maxOrange = max(oranges);

        // then
        assertThat(maxApple.getSize())
                .isEqualTo(6);

        assertThat(maxOrange.getSize())
                .isEqualTo(8);
    }

    @Test
    public void should_compare_same_type_of_fruits() {
        // given
        Apple apple1 = new Apple(3);
        Apple apple2 = new Apple(4);

        Orange orange1 = new Orange(3);
        Orange orange2 = new Orange(4);

        // when | then
        int a = apple1.compareTo(apple2);
        int b = orange1.compareTo(orange2);
        // compile error: Required type Apple, provided Orange
        // apple1.compareTo(orange1);
    }

    @Test
    public void should_create_city() {
        City laCity = new City()
                .setName("LA")
                .setSquare(100);
    }
}

class Fruit<T extends Fruit<T>> implements Comparable<T> {
    private final Integer size;

    public Fruit(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    /**
     * This allows us to get rid of the repeated code of compareTo() method
     * and also allows us to compare the fruits of the same types,
     * apples with apples and oranges with oranges.
     */
    @Override
    public int compareTo(T other) {
        return size.compareTo(other.getSize());
    }
}

class Apple extends Fruit<Apple> {
    public Apple(Integer size) {
        super(size);
    }
}

// Caveat
class Plum extends Fruit<Apple> {
    public Plum(Integer size) {
        super(size);
    }
}

class Orange extends Fruit<Orange> {
    public Orange(Integer size) {
        super(size);
    }
}

abstract class Node<T extends Node<T>> {
    String name;

    public T setName(String name) {
        this.name = name;
        return self();
    }

    protected abstract T self();
}

class City extends Node<City> {
    private int square;

    City setSquare(int square) {
        this.square = square;
        return self();
    }

    @Override
    protected City self() {
        return this;
    }
}