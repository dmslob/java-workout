package com.dmslob.generic;

import java.util.ArrayList;

public class ExtendsExample {

    public static void main(String[] args) {

        Integer[] integers = {1, 2, 3, 4, 5};
        ArrayList<Integer> integerList = new ArrayList<>();

        for (Integer element : integers) {
            integerList.add(element);
        }
        System.out.println("integerList contains: " + integerList);
        System.out.println("Total of the elements in integerList: " + sum(integerList));
        System.out.println();


        Double[] doubles = {1.1, 3.3, 5.5};
        ArrayList<Double> doubleList = new ArrayList<>();

        for (Double element : doubles) {
            doubleList.add(element);
        }
        System.out.println("doubleList contains: " + doubleList);
        System.out.println("Total of the elements in doubleList: " + sum(doubleList));
        System.out.println();


        Number[] numbers = {1, 2.4, 3, 4.1};
        ArrayList<Number> numberList = new ArrayList<>();

        for (Number element : numbers) {
            numberList.add(element);
        }
        System.out.println("numberList contains: " + numberList);
        System.out.println("Total of the elements in numberList: " + sum(numberList));
    }

    public static double sum(ArrayList<? extends Number> list) {
        double total = 0;
        for (Number element : list) {
            total += element.doubleValue();
        }
        return total;
    }
}

interface Bar<T> {
}

interface SpecificBar {
}

class Foo<T extends Bar<T> & SpecificBar> {
} // OK

// class Foo1<T extends Bar<T> & Bar<Object> & Bar1> { } // compile error, Interface has to be only one
