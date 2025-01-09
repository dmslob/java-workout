package com.dmslob;

public class Autoboxing {

    public static void main(String[] args) {
        Integer s = null;
        String sd = s + "Hello";
        System.out.println(sd);

        int n = 7;
        Integer integerN = n;
        Integer a = 345;
        System.out.println(a);
        // Double doubleN = n; // incompatible types
        Double d = (double) n; // OK
        Double doubleN = Double.valueOf(n); // OK

        // Autoboxing operations
        int x = 7;
        Integer integerX = Integer.valueOf(x);
        int y = integerX.intValue();
    }
}

@FunctionalInterface
interface M {

    int targetMethod();
    //int targetMethod2();

    boolean equals(Object obj);
}
