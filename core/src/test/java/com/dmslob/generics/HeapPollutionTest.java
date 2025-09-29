package com.dmslob.generics;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class HeapPollutionTest {
    @Test
    void should_not_cast_string_to_integer() {
        // given
        List<String> listOfString = new ArrayList<>();
        listOfString.add("Test");

        List<Integer> listOfInteger = (List<Integer>) (Object) listOfString;
        // when
        Integer firstElement = listOfInteger.getFirst();
        // then
        // java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer
        // (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap')
    }

    @Test
    void should_not_cast_integer_to_string() {
        // given
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        list1.add("1");
        list2.add("2");
        list3.add("3");
        // when
        merge(list1, list2, list3);
        // then
        // java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
        // (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
    }

    void merge(List<String>... stringList) {
        // Here we are an array of type Object holds
        // reference of an array of type List<String>
        Object[] objects = stringList;
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(420);
        // Here we are trying to assign temp
        // of type List<Integer> into arr[0]
        // which is of type List<String>
        // Here ClassCastException will be thrown
        objects[0] = temp;
        String firstEle = stringList[0].getFirst();
        System.out.println(firstEle);
    }

    @Test
    public void should_get_ClassCastException() {
        // given
        List<String> stringListA = new ArrayList<>();
        List<String> stringListB = new ArrayList<>();
        // when | then
        CollectionBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
        CollectionBuilder.addToList(stringListB, "Ten", "Eleven", "Twelve");
        List<List<String>> listOfStringLists = new ArrayList<>();

        assertThatThrownBy(() -> {
            CollectionBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
        }).isInstanceOf(ClassCastException.class);
    }
}

class CollectionBuilder {
    public static <T> void addToList(List<T> listArg, T... elements) {
        for (T x : elements) {
            listArg.add(x);
        }
    }

    public static void faultyMethod(List<String>... stringParams) {
        Object[] objectArray = stringParams;     // Valid
        objectArray[0] = Arrays.asList(42);
        String s = stringParams[0].getFirst();       // ClassCastException thrown here
    }
}
