package com.dmslob.collection.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySizeDemo {

    public static void main(String[] args) {
        ArraySizeDemo sizeDemo = new ArraySizeDemo();
        //sizeDemo.arrayLength();
        sizeDemo.arrayListSize();
    }

    void arrayLength() {
        int[] ints = new int[10];
        System.out.println(ints.length);

        ints[0] = 1;
        ints[9] = 9;

        System.out.println(Arrays.toString(ints));

        ints[0] = 0;

        System.out.println(ints.length);
    }

    void arrayListSize() {

        List<Integer> integerList = new ArrayList<>(10);
        System.out.println(integerList.size());

        Integer one = new Integer(1);
        integerList.add(one);
        System.out.println(integerList.size());

        integerList.remove(one);
        System.out.println(integerList.size());
    }
}
