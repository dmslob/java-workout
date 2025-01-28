package com.dmslob.generics;

import java.util.List;

public class GenericMethodsDemo {

    // Generic singleton factory pattern
    private static UnaryFunction<Object> IDENTITY_FUNCTION =
            new UnaryFunction<Object>() {
                public Object apply(Object arg) {
                    return arg;
                }
            };

    // IDENTITY_FUNCTION is stateless and its type parameter is
    // unbounded so it's safe to share one instance across all types.
    @SuppressWarnings("unchecked")
    public static <T> UnaryFunction<T> identityFunction() {
        return (UnaryFunction<T>) IDENTITY_FUNCTION;
    }

    public static void main(String[] args) {
        String[] strings = {"jute", "hemp", "nylon"};
        UnaryFunction<String> sameString = identityFunction();
        for (String s : strings) {
            System.out.println(sameString.apply(s));
        }
        Number[] numbers = {1, 2.0, 3L};
        UnaryFunction<Number> sameNumber = identityFunction();
        for (Number n : numbers) {
            System.out.println(sameNumber.apply(n));
        }
    }

    public void swap(List<?> list, int i, int j) {
        // list.set(i, list.set(j, list.get(i))); // Compile error
        swapHelper(list, i, j);
    }

    // Private helper method for wildcard capture
    private <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}

interface UnaryFunction<T> {
    T apply(T arg);
}
