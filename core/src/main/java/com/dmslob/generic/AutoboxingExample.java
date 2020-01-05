package com.dmslob.generic;

import java.util.ArrayList;

public class AutoboxingExample {

    public static void main(String[] args) {
        AutoboxingExample example = new AutoboxingExample();
        example.autoboxing();
    }

    public static double sum(ArrayList<Number> list) {
        double total = 0;
        for (Number element : list) {
            total += element.doubleValue();
        }
        return total;
    }

    public void autoboxing() {
        // Integers and Doubles
        Number[] numbers = {1, 2.4, 3, 4.1};
        ArrayList<Number> numberList = new ArrayList<>();

        for (Number element : numbers) {
            numberList.add(element);
        }

        System.out.println("numberList contains: " + numberList);
        System.out.println("Total of the elements in numberList: " + sum(numberList));
    }

    public static void errorExample() {
        Integer[] integers = {1, 2, 3, 4};
        ArrayList<Integer> integerList = new ArrayList<>();

        for (Integer element : integers) {
            integerList.add(element);
        }
        System.out.println("integerList contains: " + integerList);
        //System.out.println("Total of the elements in integerList: " + sum(integerList)); // compile error
    }
}
