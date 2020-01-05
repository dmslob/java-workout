package com.dmslob.oca.oop;

public class ReturningValues {

    public static void main(String[] args) {
        int number = 1;
        String letters = "abc";
        number(number);
        System.out.println(number); // 1
        letters(letters);
        System.out.println(letters); // abc
        letters = letters(letters);
        System.out.println(letters); // abcd
    }

    public static int number(int number) {
        number++;
        return number;
    }

    public static String letters(String letters) {
        letters += "d";
        return letters;
    }
}
