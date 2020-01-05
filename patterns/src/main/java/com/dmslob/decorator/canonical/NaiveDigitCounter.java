package com.dmslob.decorator.canonical;

public class NaiveDigitCounter implements DigitCounter {

    public int count(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }
}
