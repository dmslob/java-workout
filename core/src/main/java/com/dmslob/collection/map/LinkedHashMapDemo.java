package com.dmslob.collection.map;

import java.util.LinkedHashMap;
import java.util.Set;

public class LinkedHashMapDemo {

    void getOrderedSet() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, null);
        linkedHashMap.put(2, null);
        linkedHashMap.put(3, null);
        linkedHashMap.put(4, null);
        linkedHashMap.put(5, null);

        Set<Integer> keys = linkedHashMap.keySet();
        Integer[] arr = keys.toArray(new Integer[0]);

        for (int i = 0; i < arr.length; i++) {
            System.out.println("" + new Integer(i + 1) + ", " + arr[i]);
        }
    }

    void accessOrder() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(16, .75f, true);
        linkedHashMap.put(null, null);
        linkedHashMap.put(1, null);
        linkedHashMap.put(2, null);
        linkedHashMap.put(3, null);
        linkedHashMap.put(4, null);
        linkedHashMap.put(5, null);

        Set<Integer> keys = linkedHashMap.keySet();
        System.out.println(keys.toString());

        linkedHashMap.get(4);
        System.out.println(keys.toString());

        linkedHashMap.get(1);
        System.out.println(keys.toString());

        linkedHashMap.get(3);
        System.out.println(keys.toString());
    }

    void removesEldestEntry() {
        LinkedHashMap<Integer, String> linkedHashMap = new MyLinkedHashMap<>(16, .75f, true);
        linkedHashMap.put(1, null);
        linkedHashMap.put(2, null);
        linkedHashMap.put(3, null);
        linkedHashMap.put(4, null);
        linkedHashMap.put(5, null);
        Set<Integer> keys = linkedHashMap.keySet();
        System.out.println(keys.toString());

        linkedHashMap.put(6, null);
        System.out.println(keys.toString());

        linkedHashMap.put(7, null);
        System.out.println(keys.toString());

        linkedHashMap.put(8, null);
        System.out.println(keys.toString());
    }

    public static void main(String[] args) {
        LinkedHashMapDemo linkedHashMapDemo = new LinkedHashMapDemo();
        //linkedHashMapDemo.getOrderedSet();
        linkedHashMapDemo.accessOrder();
        //linkedHashMapDemo.removesEldestEntry();
    }
}
