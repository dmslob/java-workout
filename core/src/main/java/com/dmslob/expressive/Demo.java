package com.dmslob.expressive;

import java.math.BigDecimal;

public class Demo {

    public static void main(String[] args) {
        //System.out.println(isOdd(3));

        //change();

        //longDivision();

        //usingSmallLetterForLong();
    }

    public static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    public static void change() {
        // but how could it know that you wanted two digits after the decimal point?
        System.out.println(2.00 - 1.10); // 0.8999999999999999

        // Poor solution - still uses binary floating-point!
        System.out.printf("%.2f%n", 2.00 - 1.10);

        System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));
    }

    public static void longDivision() {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        System.out.println(MICROS_PER_DAY);

        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        System.out.println(MILLIS_PER_DAY);

        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }

    public static void usingSmallLetterForLong() {
        System.out.println(12345 + 54321); // 66666

        // Second operand is long
        System.out.println(12345 + 5432l); // 17777
    }
}
