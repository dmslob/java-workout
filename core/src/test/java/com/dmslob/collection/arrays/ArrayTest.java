package com.dmslob.collection.arrays;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ArrayTest {

    @Test
    public void should_have_the_same_size() {
        String[] cars = new String[] {"BMW", "Ford", "Lexus"};
        Assert.assertEquals(3, cars.length);

        cars[0] = null;
        Assert.assertEquals(3, cars.length);
    }

    /**
     * Covariance example
     */
    @Test(expected = ArrayStoreException.class)
    public void covarianceTest() {
        Integer[] integers = {1, 2, 3};
        Number[] numbers = integers; // valid
        Number firstElement = numbers[0]; // valid

        assert firstElement instanceof Integer;
        numbers[0] = 4L; // throws ArrayStoreException at runtime
    }

    @Test
    public void invarianceTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        // integers.add(4); // java.lang.UnsupportedOperationException
        // List<Number> numbers = integers; // compile error

        List<? extends Number> numbers = integers;
        Number firstElement = numbers.get(0);

        Integer expectedValue = 1;
        assert firstElement.equals(expectedValue);
        //numbers.set(0, 4L); // compile error
    }
}
