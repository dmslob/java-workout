package com.dmslob.collection.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ArrayTest {

    @Test(expected = ArrayStoreException.class)
    public void covarianceTest() {
        Integer[] integerArray = {1, 2, 3};
        Number[] numberArray = integerArray; // valid
        Number firstElement = numberArray[0]; // valid
        assert firstElement instanceof Integer;
        numberArray[0] = 4L; // throws ArrayStoreException at runtime
    }

    @Test
    public void invarianceTest() {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        //List<Number> numberList = integerList; // compile error
        List<? extends Number> numberList = integerList;
        Number firstElement = numberList.get(0);
        //numberList.set(0, 4L); // compile error
    }
}
