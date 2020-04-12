package com.dmslob.collection;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CompareForEachIterations {

    private void streamForEach() {
        List<String> listOfLetters = new ArrayList<>();
        listOfLetters.addAll(Arrays.asList("A", "B", "C", "D"));

        Consumer<String> removeElement = s -> {
            System.out.println(s);
            if (s != null && s.equals("A")) {
                //listOfLetters.add("D"); //java.util.ConcurrentModificationException
                //listOfLetters.remove("D"); //java.util.ConcurrentModificationException
                //listOfLetters.set(3, "G");
            }
        };
        // we continue iterating over the whole list before we see an exception
        listOfLetters.stream().forEach(removeElement);

        System.out.println(listOfLetters);
    }

    private void forEach() {
        List<String> listOfLetters = new ArrayList<>();
        listOfLetters.addAll(Arrays.asList("A", "B", "C", "D"));

        for (String letter : listOfLetters) {
            //listOfLetters.add("D"); //java.util.ConcurrentModificationException
            //listOfLetters.remove("D"); //java.util.ConcurrentModificationException
            listOfLetters.set(3, "E");
        }

        listOfLetters.forEach(s -> {
            //listOfLetters.add("D"); //java.util.ConcurrentModificationException
            //listOfLetters.remove("D"); //java.util.ConcurrentModificationException
            listOfLetters.set(3, "G");
        });

        Consumer<String> removeElement = s -> {
            System.out.println(s);
            if (s != null && s.equals("A")) {
                //listOfLetters.add("D"); //java.util.ConcurrentModificationException
                //listOfLetters.remove("D"); //java.util.ConcurrentModificationException
                listOfLetters.set(3, "G");
            }
        };
        // forEach() is fail-fast
        listOfLetters.forEach(removeElement);
    }

    public static void main(String[] args) {
        CompareForEachIterations forEachIterations = new CompareForEachIterations();
        forEachIterations.forEach();
        //forEachIterations.streamForEach();
    }
}
