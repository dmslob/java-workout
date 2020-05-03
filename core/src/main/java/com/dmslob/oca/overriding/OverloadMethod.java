package com.dmslob.oca.overriding;

public class OverloadMethod {
    public static String test(byte b) {
        return "Byte";
    }

    public static String test(char c) {
        return "Char";
    }

    public static String test(int i) {
        return "Int";
    }
}
