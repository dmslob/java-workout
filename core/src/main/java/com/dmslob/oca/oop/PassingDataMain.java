package com.dmslob.oca.oop;

public class PassingDataMain {

    public static void main(String[] args) {
        int n = 8;
        newNumber(n);
        System.out.println(n);

        Integer original1 = 1;
        Integer original2 = 2;
        swap(original1, original2);
        System.out.println(original1); // 1
        System.out.println(original2); // 2

        StringBuilder builder = new StringBuilder("1");
        System.out.println(builder.toString());
        addString(builder);
        System.out.println(builder.toString());
    }

    static void addString(StringBuilder builder) {
        builder.append("2");
    }

    static void newNumber(int n) {
        n = 8;
    }

    static void swap(Integer a, Integer b) {
        Integer temp = a;
        a = b;
        b = temp;
    }

}