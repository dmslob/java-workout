package com.dmslob.generics;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CovariantArraysTest {
    static class Fruit {
    }

    static class Apple extends Fruit {
    }

    static class Jonathan extends Apple {
    }

    static class Orange extends Fruit {
    }

    @Test
    public void should_test_covariance() {
        // given
        Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple(); // OK
        fruits[1] = new Jonathan(); // OK
        System.out.println(fruits.getClass());
        // when | then
        // Runtime type is Apple[], not Fruit[] or Orange[]:
        assertThatThrownBy(() -> {
            fruits[0] = new Fruit();
        }).isInstanceOf(ArrayStoreException.class);

        assertThatThrownBy(() -> {
            // Compiler allows you to add Oranges:
            fruits[0] = new Orange(); // ArrayStoreException
        }).isInstanceOf(ArrayStoreException.class);

        //Compile Error: incompatible types:
        //List<Fruit> fruitList = new ArrayList<Apple>();

        // Wildcards allow covariance:
        List<? extends Fruit> fruitList = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        // fruitList.add(new Apple());
        // fruitList.add(new Fruit());
        // fruitList.add(new Object());
        fruitList.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit:
        Fruit fruit = fruitList.get(0);
    }
}