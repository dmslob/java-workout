package com.dmslob.exception;

import static java.lang.Double.POSITIVE_INFINITY;

public class DivisionByZero {

    public static void main(String[] args) {
        try {
            int i = 7;
            float f = 12.2f;
            double d = 8098098.8790d;
            //System.out.println(i / 0);
            System.out.println(f / 0);
            System.out.println(d / 0);
            System.out.println(POSITIVE_INFINITY == (f / 0));
            System.out.println(POSITIVE_INFINITY == (d / 0));
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException");
        } catch (ArithmeticException ex) {
            System.out.println("ArithmeticException");
        }
    }
}
