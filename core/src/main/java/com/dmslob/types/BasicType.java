package com.dmslob.types;

public class BasicType {

    void values() {
        int octal = 017;
        System.out.println(octal);

        int hexadecimal = 0xFF;
        System.out.println(hexadecimal);

        int binary = 0b10;
        System.out.println(binary);
    }

    public static void main(String[] args) {
        BasicType basicTypeMain = new BasicType();
        basicTypeMain.values();

        System.out.println(basicTypeMain.countBits(5));
        System.out.println(basicTypeMain.power(2, 3));
        System.out.println(basicTypeMain.reverse(4567));

    }

    long reverse(int x) {
        long result = 0;
        long xRemaining = Math.abs(x);
        while (xRemaining != 0) {
            result = result * 10 + xRemaining % 10;
            xRemaining /= 10;
        }
        return x < 0 ? -result : result;
    }

    short countBits(int x) {
        short numBits = 0;
        while (x != 0) {
            numBits += (x & 1);
            x >>>= 1;
        }
        return numBits;
    }

    double power(double x, int y) {
        double result = 1.0;
        long power = y;
        if (y < 0) {
            power = -power;
            x = 1.0 / x;
        }
        while (power != 0) {
            if ((power & 1) != 0) {
                result *= x;
            }
            x *= x;
            power >>>= 1;
        }
        return result;
    }
}