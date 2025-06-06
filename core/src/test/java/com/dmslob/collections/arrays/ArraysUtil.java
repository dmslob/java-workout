package com.dmslob.collections.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysUtil {

    /**
     * Reverse array
     */
    static int[] reverse(int[] nums) {
        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = temp;
        }
        return nums;
    }

    /**
     * Concatenate 2 arrays
     */
    static <T> T[] concat(T[] first, T[] second) {
        assert first != null;
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * Generic N array concatenation
     */
    static <T> T[] concat(T[] first, T[]... rest) {
        assert first != null;
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;

        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    static int[] selectionSort(int[] a) {
        int n = a.length - 1;
        for (int i = 0; i < n; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
                // Swap the found minimum element with the first element
                int temp = a[minIdx];
                a[minIdx] = a[i];
                a[i] = temp;
            }
        }
        return a;
    }

    static int[] insertionSort(int[] a) {
        int in, out;
        int size = a.length;
        for (out = 1; out < size; out++) {
            int temp = a[out];
            in = out;
            while (in > 0 && a[in - 1] >= temp) {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = temp;
        }
        return a;
    }

    /**
     * Sorts an array with quicksort algorithm
     */
    static void quickSort(int[] arr, int left, int right) {
        int pivotIndex = left + (right - left) / 2;
        int pivotValue = arr[pivotIndex];
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivotValue) {
                i++;
            }
            while (arr[j] > pivotValue) {
                j--;
            }
            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
            if (left < i) {
                quickSort(arr, left, j);
            }
            if (right > i) {
                quickSort(arr, i, right);
            }
        }
    }

    static int binarySearch(int[] integers, int key) {
        int left = 0;
        int right = integers.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (integers[mid] == key) {
                return mid;
            } else if (integers[mid] < key) {
                left = mid + 1;

            } else if (integers[mid] > key) {
                right = mid - 1;
            }
        }
        return -1;
    }

    static int recursiveBinarySearch(int[] arr, int left, int right, int key) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] > key) {
                return recursiveBinarySearch(arr, left, mid - 1, key);
            }
            return recursiveBinarySearch(arr, mid + 1, right, key);
        }
        return -1;
    }

    /**
     * KMP Search refers to the Knuth–Morris–Pratt (KMP) algorithm,
     * which is a way to search for a pattern inside a text.
     * Instead of checking every possible position in the text (which would be slow),
     * KMP speeds things up by "remembering" where it can restart the search
     * when a mismatch happens — without rechecking characters it already knows matched.
     */
    static List<Integer> performKMPSearch(String text, String pattern) {
        int[] lps = buildLps(pattern);
        int textIndex = 0;
        int patternIndex = 0;
        List<Integer> foundIndexes = new ArrayList<>();
        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                patternIndex++;
                textIndex++;
            }
            if (patternIndex == pattern.length()) {
                foundIndexes.add(textIndex - patternIndex);
                patternIndex = lps[patternIndex - 1];
            } else if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                if (patternIndex != 0)
                    patternIndex = lps[patternIndex - 1];
                else
                    textIndex = textIndex + 1;
            }
        }
        return foundIndexes;
    }

    // Build the LPS (Longest Prefix Suffix) array
    private static int[] buildLps(String pattern) {
        int patternLength = pattern.length();
        int len = 0; // length of the previous longest prefix suffix
        int i = 1;
        int[] lps = new int[patternLength];
        lps[0] = 0; // lps[0] is always 0
        while (i < patternLength) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * Exponential Search (also called exponential binary search
     * It’s an algorithm to search for an element in a sorted array.
     * it quickly finds a range where the element could be,
     * by exponentially increasing the index (like 1, 2, 4, 8, 16, etc.).
     * Then it applies binary search inside that small range.
     */
    static int exponentialSearch(int[] integers, int elementToSearch) {
        if (integers[0] == elementToSearch)
            return 0;
        if (integers[integers.length - 1] == elementToSearch)
            return integers.length;
        int range = 1;
        while (range < integers.length && integers[range] <= elementToSearch) {
            range = range * 2;
        }
        return Arrays.binarySearch(integers, range / 2, Math.min(range, integers.length), elementToSearch);
    }

    /**
     * It’s a search algorithm for sorted arrays, like binary search — but smarter
     * when data is uniformly distributed (evenly spaced out, like 10, 20, 30, 40, etc).
     * Instead of always cutting the array in half (like binary search),
     * it guesses where the key might be — based on the value of the key.
     * It’s like saying:
     * "If the value is closer to the start,
     * don’t search in the middle — search near the beginning!"
     */
    static int interpolationSearch(int[] integers, int elementToSearch) {
        int startIndex = 0;
        int lastIndex = (integers.length - 1);
        while ((startIndex <= lastIndex) && (elementToSearch >= integers[startIndex]) &&
                (elementToSearch <= integers[lastIndex])) {
            int pos = startIndex + (((lastIndex - startIndex) / (integers[lastIndex] - integers[startIndex])) *
                    (elementToSearch - integers[startIndex]));
            if (integers[pos] == elementToSearch)
                return pos;
            if (integers[pos] < elementToSearch)
                startIndex = pos + 1;
            else
                lastIndex = pos - 1;
        }
        return -1;
    }

    /**
     * Jump-Search — it's a nice middle ground between linear and binary search.
     * It's used on sorted arrays.
     * Instead of checking every element one by one (like linear search),
     * you jump ahead by fixed steps to find the block where the element might be.
     * Then you do a linear search inside that small block.
     */
    static int jumpSearch(int[] integers, int elementToSearch) {
        int arrayLength = integers.length;
        int jumpStep = (int) Math.sqrt(integers.length);
        int previousStep = 0;
        while (integers[Math.min(jumpStep, arrayLength) - 1] < elementToSearch) {
            previousStep = jumpStep;
            jumpStep += (int) (Math.sqrt(arrayLength));
            if (previousStep >= arrayLength) {
                return -1;
            }
        }
        while (integers[previousStep] < elementToSearch) {
            previousStep++;
            if (previousStep == Math.min(jumpStep, arrayLength)) {
                return -1;
            }
        }
        if (integers[previousStep] == elementToSearch) {
            return previousStep;
        }
        return -1;
    }

    static boolean isSorted(String[] strings) {
        assert strings != null;
        for (int i = 1; i < strings.length; i++) {
            if (strings[i - 1].compareTo(strings[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
