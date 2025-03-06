package com.dmslob.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Snippets {

    public static int reverseInt(int integer) {
        int reversedInt = 0;
        while (integer != 0) {
            int digit = integer % 10;
            reversedInt = reversedInt * 10 + digit;
            integer /= 10;
        }
        return reversedInt;
    }

    /**
     * Gets an array of numbers and look for a unique number
     *
     * @return number
     */
    public static int findUniqueNumber(int[] inputArray) {
        int number = 0;
        for (int i = 0; i < inputArray.length; i++) {
            number = number ^ inputArray[i];
        }
        return number;
    }

    public static int[] uniqueNumbers(int[] numbers) {
        return numbers;
    }

    public static void main(String[] args) {
        var numbers = new int[]{56, 2, 3, 5, 9, 3, 1, 9, 11};
        var result = removeDuplicates(numbers);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
    }

    public static int[] findIntersection(int[][] numbers) {
        var result = new ArrayList<Integer>();
        var totalCounter = new int[1000];
        for (int i = 0; i < numbers.length; i++) {
            var currentCounter = new boolean[1000];
            for (int j = 0; j < numbers[i].length; j++) {
                var index = numbers[i][j] - 1;
                if (!currentCounter[index]) {
                    currentCounter[index] = true;
                    totalCounter[index]++;
                }
            }
        }
        for (int i = 0; i < totalCounter.length; i++) {
            if (totalCounter[i] == numbers.length) {
                result.add(i + 1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] findIntersections(int[][] numbers) {
        var result = new ArrayList<Integer>(100);
        var counter = new int[1000];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                var value = numbers[i][j];
                var index = value - 1;
                counter[index] = counter[index] + 1;
            }
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] == numbers.length) {
                result.add(i + 1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] removeDuplicates(int[] a) {
        var n = a.length;
        if (n == 0 || n == 1) return a;

        var counter = 0;
        Arrays.sort(a);

        for (int i = 0; i < n - 1; i++) {
            if (a[i] != a[i + 1]) counter++;
        }

        var result = new int[counter + 1];
        int j = 0;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] != a[i + 1]) {
                result[j++] = a[i];
            }
        }
        result[j++] = a[n - 1];

        return result;
    }

    /**
     * Factorial
     *
     * @param n for which factorial is to be calculated for
     * @return factorial
     */
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
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
     * @return maximum
     */
    public static <T extends Comparable<T>> T max(T x, T y, T z) {
        T max = x;

        if (y.compareTo(max) > 0) {
            max = y;
        }

        if (z.compareTo(max) > 0) {
            max = z;
        }

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
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
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
            int tmp = Integer.parseInt(s[1]);
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
