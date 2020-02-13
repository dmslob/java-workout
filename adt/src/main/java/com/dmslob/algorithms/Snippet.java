package com.dmslob.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snippet {

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println("reverseString: " + reverseString(str));

        int number = 1234;
        System.out.println("reverseInteger: " + reverseInteger(number));

        int n = 10;
        System.out.println("factorial of " + n + " is " + factorial(n));
        System.out.println(("rfibonacci of " + n + " is ") + fibonacci(n));

        System.out.println(max(2, 3, 4));

        for (int i = 0; i < 20; i++) {
            boolean isPrime = isPrime(i);
            if (isPrime) {
                System.out.println(i);
            }
        }
    }

    public static int reverseInteger(int number) {
        int reversedNumber = 0;
        while (number != 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }
        return reversedNumber;
    }

    /**
     * Checks if given string is palindrome (same forward and backward)
     * Skips non-letter characters
     *
     * @param s string to check
     * @return true if palindrome
     */
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            }
        }
        String forward = sb.toString().toLowerCase();
        String backward = sb.reverse().toString().toLowerCase();
        return forward.equals(backward);
    }

    /**
     * Takes a number and checks whether it is a palindrome
     *
     * @param number
     * @return true if palindrom
     */
    public static boolean isPalindrome(int number) {
        int k = 0;
        int input = number;
        boolean isPoly = false;
        while (number > 0) {
            k = k * 10 + (number % 10);
            number = number / 10;
        }

        if (k == input) {
            isPoly = true;
        }
        return isPoly;
    }

    /**
     * Gets an array of numbers and look for a unique number
     *
     * @param inputArray
     * @return number
     */
    public static int findUniqueNumber(int[] inputArray) {
        int number = 0;
        for (int i = 0; i < inputArray.length; i++) {
            number = number ^ inputArray[i];
        }
        return number;
    }

    /**
     * Recursive Fibonacci series
     *
     * @param n
     * @return fibonacci number for given n
     */
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 2) + fibonacci(n - 1);
    }

    /**
     * Factorial
     *
     * @param n for which factorial is to be calculated for
     * @return factorial
     */
    public static int factorial(int n) {
        if (n == 0)
            return 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Generate random lottery numbers
     *
     * @param numNumbers    how many performLottery numbers are available (e.g. 49)
     * @param numbersToPick how many numbers the player needs to pick (e.g. 6)
     * @return array with the random numbers
     */
    public static Integer[] performLottery(int numNumbers, int numbersToPick) {
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < numNumbers; i++) {
            numbers.add(i + 1);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, numbersToPick).toArray(new Integer[numbersToPick]);
    }

    /**
     * Find maximum
     *
     * @param x
     * @param y
     * @param z
     * @return maximum
     */
    public static <T extends Comparable<T>> T max(T x, T y, T z) {
        T max = x;

        if (y.compareTo(max) > 0)
            max = y;

        if (z.compareTo(max) > 0)
            max = z;

        return max;
    }

    public static int max(int a, int b, int c) {
        int res = a > (b > c ? b : c) ? a : (b > c ? b : c);
        return res;
    }

    // returns the index of the search key
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // or int mid = (low + high) >>> 1;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static int isTriangle(int n) {
        int total = 0;
        while (n > 0) {
            total = total + n;
            --n;
            System.out.println(total);
        }
        return total; // 5 9 12 14 15
    }

    public static int pow(int n, int pow) {
        int t = 1;
        for (int i = 0; i < pow; i++) {
            t = t * n;
        }
        return t;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // size of numbers after comma
    public static int lengthAfterComma(double f) {
        int len;
        if (String.valueOf(f).contains("E")) {
            String[] s = String.valueOf(f).split("\\.")[1].split("E");
            int tmp = Integer.valueOf(s[1]);
            len = s[0].length();
            if (tmp - len > 0) {
                len += tmp - len;
            }
        } else {
            len = String.valueOf(f).split("\\.")[1].length();
        }
        return len;
    }

    public static boolean isPrime(int number) {
        // Corner case
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String reverseStringByStringBulder(String strParam) {
        return new StringBuilder(strParam).reverse().toString();
    }

    public static String reverseString(String strParam) {
        char[] chars = strParam.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }
        return new String(chars);
    }
}
