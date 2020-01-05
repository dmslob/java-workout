package com.dmslob.lambdas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaMain {

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");

        stringList.stream().map(item -> {
            try {
                return doWork(item);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
        // or
        stringList.stream().map(LambdaMain::doWorkWithoutException).forEach(System.out::println);
        // or
        // The only problem left is that when an exception occurs, the processing of the your stream stops immediately.
        // If that is no problem for you, then go for it. I can imagine, however, that direct termination is not ideal in many situations.
        stringList.stream().map(wrap(item -> doWork(item))).forEach(System.out::println);
        // or
        stringList.stream().map(Either.lift(item -> doWork(item))).forEach(System.out::println);
        // or
        stringList.stream().map(Either.liftWithValue(item -> doWork(item))).forEach(System.out::println);


//        Arrays.asList("http://localhost/", "https://github.com").stream()
//                .map(URL::new) // Unhandled exception
//                .collect(Collectors.toList());

    }

    static String doWork(String s) throws Exception {
        if ("one".equals(s))
            throw new Exception("Exception");
        return s;
    }

    static String doWorkWithoutException(String s) {
        try {
            return doWork(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T, R> Function<T, R> wrap(CheckedFunction<T, R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}

