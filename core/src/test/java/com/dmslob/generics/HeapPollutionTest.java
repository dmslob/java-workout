package com.dmslob.generics;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HeapPollutionTest {
    @Test
    void should_not_cast_string_to_integer() {
        // given
        List<String> listOfString = new ArrayList<>();
        listOfString.add("Test");

        List<Integer> listOfInteger = (List<Integer>) (Object) listOfString;
        // when
        Integer firstElement = listOfInteger.get(0);
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
        String firstEle = stringList[0].get(0);
        System.out.println(firstEle);
    }
}
