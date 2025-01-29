package com.dmslob.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModificationExceptionExample {

    public static void main(String[] args) {
        ConcurrentModificationExceptionExample example = new ConcurrentModificationExceptionExample();
        //example.getConcurrentModificationException();
        example.avoidConcurrentModificationException();
    }

    void getConcurrentModificationException() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");

        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            String value = it.next();
            System.out.println("List Value:" + value);
            if (value.equals("3")) {
                //strings.remove(value); // Any modification of collection causes ConcurrentModificationException
                //strings.add("55"); // ConcurrentModificationException
                it.remove(); // OK
            }
        }
        System.out.println(strings.toString());
    }

    void testUnsupportedOperationException() {
        CopyOnWriteArrayList<String> myList = new CopyOnWriteArrayList<String>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        Iterator<String> iterator = myList.listIterator();
        myList.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            if ("5".equals(val)) {
                // java.lang.UnsupportedOperationException
                iterator.remove();
                //((ListIterator<String>) iterator).add("45");
            }
        }
    }

    void avoidConcurrentModificationException() {
        CopyOnWriteArrayList<String> myList = new CopyOnWriteArrayList<>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        for (String s : myList) {
            if ("2".equals(s)) {
                myList.remove(s);
            }
        }

        Iterator<String> listIterator = myList.iterator();
        while (listIterator.hasNext()) {
            String value = listIterator.next();
            if (value.equals("3")) {
                myList.remove("4");
                myList.add("6");
                myList.add("7");
            }
        }

        System.out.println("List:" + myList.toString());

        Map<String, String> myMap = new ConcurrentHashMap<String, String>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        myMap.put("3", "3");

        Iterator<String> mapIterator = myMap.keySet().iterator();
        while (mapIterator.hasNext()) {
            String key = mapIterator.next();
            if (key.equals("1")) {
                myMap.remove("3");
                myMap.put("4", "4");
                myMap.put("5", "5");
            }
        }
        System.out.println("Map:" + myMap.toString());
    }
}
