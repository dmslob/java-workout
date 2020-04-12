package com.dmslob.functional;

import java.util.function.Predicate;

public class FunctionalDemo {

    public static void main(String[] args) {

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> stringPredicate = s -> s.isEmpty();
    }
}
