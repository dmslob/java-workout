package com.dmslob.generics;

public class GenericMethodExample {

    public static void main(String[] args) {

        Integer[] integerArray = {1, 2, 3, 4, 5, 6};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        Character[] characterArray = {'H', 'E', 'L', 'L', 'O'};

        System.out.println(" integerArray:");
        printArray(integerArray);

        System.out.println("\ndoubleArray:");
        printArray(doubleArray);

        System.out.println("\ncharacterArray:");
        printArray(characterArray);

        System.out.println("Maximum: " + max(3, 4, 5));
        System.out.println("Maximum: " + max(6.6, 8.8, 7.7));
        System.out.println("Maximum: " + max("pear", "apple", "orange"));
    }

    public static <T> void printArray(T[] inputArray) {
        for (T element : inputArray) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> T max(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) max = y;
        if (z.compareTo(max) > 0) max = z;

        return max;
    }
}
