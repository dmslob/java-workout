package com.dmslob.collection;

import java.util.*;
import java.util.stream.Collectors;

public class TestMap {

    private static final HashMap<String, Integer> items = new HashMap<>();

    static {
        items.put("coins", 5);
        items.put("pens", 2);
        items.put("chairs", 7);
    }

    public static void main(String[] args) {

    }

    static void streamApi() {
        items.entrySet().stream().forEach(e -> {
            System.out.format("key: %s, value: %d%n", e.getKey(), e.getValue());
        });
    }

    static void forEach() {
        items.forEach((k, v) -> {
            System.out.format("key: %s, value: %d%n", k, v);
        });
    }

    static void enhancedFor() {
        for (Map.Entry<String, Integer> pair : items.entrySet()) {
            System.out.format("key: %s, value: %d%n", pair.getKey(), pair.getValue());
        }
    }

    static void iterator() {
        Iterator<Map.Entry<String, Integer>> it = items.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = it.next();
            System.out.format("key: %s, value: %d%n", pair.getKey(), pair.getValue());
        }
    }

    static void forIterator() {
        for (Iterator<Map.Entry<String, Integer>> it = items.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> pair = it.next();
            System.out.format("key: %s, value: %d%n", pair.getKey(), pair.getValue());
        }
    }

    static void keySetIterator() {
        Iterator<String> it = items.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.format("key: %s, value: %d%n", key, items.get(key));
        }
    }

    static void keysAndValues() {
        Set<String> keys = items.keySet();
        keys.forEach(System.out::println);

        Collection<Integer> vals = items.values();
        vals.forEach(System.out::println);
    }

    static void filter() {
        Map<String, String> capitals = new HashMap<>();

        capitals.put("svk", "Bratislava");
        capitals.put("ger", "Berlin");
        capitals.put("hun", "Budapest");
        capitals.put("czk", "Prague");
        capitals.put("pol", "Warsaw");
        capitals.put("ita", "Rome");

        Map<String, String> filteredCapitals = capitals.entrySet().stream()
                .filter(map -> map.getValue().startsWith("B"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        filteredCapitals.entrySet().forEach(System.out::println);
    }

    static void map() {
        Map<String, Double> productPrice = new HashMap<>();

        productPrice.put("Rice", 6.9);
        productPrice.put("Flour", 3.9);
        productPrice.put("Sugar", 4.9);
        productPrice.put("Milk", 3.9);
        productPrice.put("Egg", 1.9);

        Set<String> keys = productPrice.keySet();

        for (String key : keys) {
            System.out.println(key);
        }
        String.valueOf(23);
        keys.forEach(key -> System.out.println(key));

        Collection<Double> values = productPrice.values();
        // values.add(5.7); // java.lang.UnsupportedOperationException
        values.forEach(value -> System.out.println(value));

        Set<Map.Entry<String, Double>> entries = productPrice.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            // entry.setValue(45.6); // OK
            System.out.print("key: " + entry.getKey());
            System.out.println(", Value: " + entry.getValue());
        }

        String theKey = "Fish";
        //productPrice.put(theKey, 34.6);
        //Even though if the key is present, the callExpensiveMethodToFindValue will get called
        productPrice.putIfAbsent(theKey, callExpensiveMethodToFindValue(theKey));
        //The callExpensiveMethodToFindValue will never get called if key is already present
        productPrice.computeIfAbsent(theKey, key -> callExpensiveMethodToFindValue(key));

        productPrice.getOrDefault("Dog", 45.6);
    }

    static Double callExpensiveMethodToFindValue(String val) {
        System.out.println("invoked..." + val);
        return Double.valueOf(23.54);
    }
}
