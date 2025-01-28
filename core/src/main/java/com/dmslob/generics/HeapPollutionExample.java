package com.dmslob.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapPollutionExample {

    public static void main(String[] args) {

        List<String> stringListA = new ArrayList<>();
        List<String> stringListB = new ArrayList<>();

        ArrayBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
        ArrayBuilder.addToList(stringListB, "Ten", "Eleven", "Twelve");

        List<List<String>> listOfStringLists = new ArrayList<>();
        ArrayBuilder.addToList(listOfStringLists, stringListA, stringListB);

        ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
    }
}

class ArrayBuilder {
    public static <T> void addToList(List<T> listArg, T... elements) {
        for (T x : elements) {
            listArg.add(x);
        }
    }

    public static void faultyMethod(List<String>... stringParams) {
        Object[] objectArray = stringParams;     // Valid
        objectArray[0] = Arrays.asList(42);
        String s = stringParams[0].get(0);       // ClassCastException thrown here
    }
}
