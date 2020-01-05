package com.dmslob.generic;

import java.util.ArrayList;
import java.util.List;

public class CollectionsApp {

    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        numberList.add(Integer.valueOf(1));

        List<Integer> integers = new ArrayList<>();

        // Generics in Java are invariant
        // integers = numberList; // compile error
        // numberList = integers; // compile error

        //List<Integer> integers = numberList; // compile error

        List<? extends Number> readOnly = numberList;
        Number number1 = readOnly.get(0);
        //readOnly.add(Integer.valueOf(2)); // compile error

        List<? super Number> anyNumbers = numberList;
        //Number number2 = anyNumbers.get(0); // compile error
        anyNumbers.add(Integer.valueOf(2));
    }
}
