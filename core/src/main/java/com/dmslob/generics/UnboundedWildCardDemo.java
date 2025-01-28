package com.dmslob.generics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnboundedWildCardDemo {

    // List.class, String[].class, and int.class are all legal,
    // but List<String>.class and List<?>.class are not.
    public static void main(String[] args) {
        UnboundedWildCardDemo demo = new UnboundedWildCardDemo();
        demo.wildCardIllegalOperations();
        demo.covariantTypes();
    }

    public void wildCardIllegalOperations() {
        List<?> listOfObjects = new ArrayList<>();
        listOfObjects.add(null);
        // Compile error
        //listOfObjects.add(new Object());
        //listOfObjects.add("1");
        //listOfObjects.add(1);

        // Legitimate use of raw type - instanceof operator
        if (listOfObjects instanceof List) { // Raw type
            System.out.println("OK");
            List<?> copyObjects = (List<?>) listOfObjects; // Wildcard type
        }

        List<Object> objects = new ArrayList<>();
        objects.add("user");
        objects.add(new Integer(45));

        List<Integer> integers = new ArrayList<>();
        integers.add(new Integer(23));
        integers.add(new Integer(56));

        //List<Object> listOfObjects = integers; // Incompatible types
    }

    public void unboundedWildcardType() {
        Set<String> strings = new HashSet();
        strings.add("user");
        Set<Integer> integers = new HashSet();
        integers.add(new Integer(34));
        int numElementsInCommon = numElementsInCommon(strings, integers);
        System.out.println(numElementsInCommon);
    }

    public int numElementsInCommon(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result;
    }

    void unboundedCollection() {
        List unboundedList = new ArrayList<Integer>();
        List<?> unboundedListWithMark = new ArrayList<Integer>();
        //List<?> unboundedListWithMark = new ArrayList<?>();
        //List<Object> unboundedObjectList = new ArrayList<Integer>();
    }

    void covariantTypes() {
        Number[] nums = new Number[5];
        nums[0] = 1;
        nums[1] = 2.5;

        Integer[] intArr = new Integer[5];
        Number[] numArr = intArr; // Ok
        numArr[0] = 1.23; // java.lang.ArrayStoreException: java.lang.Double
    }

    void invariantTypes() {
        ArrayList<Integer> intArrList = new ArrayList<>();
        //ArrayList<Number> numArrList = intArrList; // Not ok
        ArrayList<Integer> anotherIntArrList = intArrList; // Ok
    }

    void withWildCards() {
        ArrayList<Integer> intArrList = new ArrayList<>();
        ArrayList<? super Integer> numArrList = intArrList; // Ok

    }
}