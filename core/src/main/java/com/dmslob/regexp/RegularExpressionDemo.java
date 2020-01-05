package com.dmslob.regexp;

import java.util.regex.Pattern;

public class RegularExpressionDemo {

    public static void main(String[] args) {
        int total = 5;
        int cnt = 0;
        while (cnt < total) {
            System.out.println(total);
            total = 7;
            cnt++;
        }
        // Regular expression to check if String is number or not
        //Pattern pattern = Pattern.compile(".*[^0-9].*");
        //Pattern pattern = Pattern.compile("[0-9]+");

        // Pattern pattern = Pattern.compile("[0-9]+.[0-9]+");
        // pattern = Pattern.compile("\\d+.\\d+");

        Pattern pattern = Pattern.compile("\\p{Digit}+");
        String[] inputs = {"123", "-123", "123.12", "abcd123"};

        for (String input : inputs) {
            System.out.println(input + " is number : " + !pattern.matcher(input).matches());
        }

        // Regular expression to check if String is 6 digit number or not
        String[] numbers = {"123", "1234", "123.12", "abcd123", "123456"};
        Pattern digitPattern = Pattern.compile("\\d{6}");

        for (String number : numbers) {
            System.out.println(number + " is 6 digit number : " + digitPattern.matcher(number).matches());
        }
    }

    boolean isStringIsNumber(String input) {
        Pattern pattern = Pattern.compile(".*[^0-9].*");
        return pattern.matcher(input).matches();
    }
}
