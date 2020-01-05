package com.dmslob.types;

public class BasicType {

    public static void main(String[] args) {
        System.out.println(new BasicType().countBits(5));
        System.out.println(new BasicType().power(2, 3));
        System.out.println(new BasicType().reverse(4567));
        System.out.println(new BasicType().isPalindromeNumber(656));

        int x = 25;
        // y will equal 9 since the expression x == 10 evaluates to false
        boolean f = (x == 10);
        System.out.println(f);
        int y = (x == 10) ? 5 : 9;
        System.out.println(y);
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

    boolean isPalindromeNumber(int x) {
        if (x < 8) {
            return false;
        }
        final int numDigits = (int) (Math.floor(Math.log10(x))) + 1;
        int msdMask = (int) Math.pow(10, numDigits - 1);
        for (int i = 0;
             i < (numDigits / 2);
             ++i) {
            if (x / msdMask != x % 10) {
                return false;
            }
            x %= msdMask; // Remove the most significant digit of x.
            x /= 10; // Remove the least significant digit of x.
            msdMask /= 100;
        }
        return true;
    }
}

class Test<I extends Integer> {
    <L extends Long> void x(I i, L l) {
        System.out.println(i.intValue() + ", " + l.longValue());
    }
}
