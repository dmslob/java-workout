package com.dmslob.collection.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArraySnippet {

    /**
     * Generic 2 array concatenation
     *
     * @return concatenated array
     */
    public static <T> T[] arrayConcat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static void reverseArray() {
        int[] array = {1, 2, 3};

        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }

        System.out.println(Arrays.toString(array));
    }

    /**
     * Generic N array concatenation
     *
     * @param first is the first array (not null)
     * @param rest  the rest of the arrays (optional)
     * @param <T>   the element type
     * @return concatenated array
     */
    public static <T> T[] nArrayConcat(T[] first, T[]... rest) {
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
        int n = arr.length - 1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] selectionSort(int[] a) {
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

    public static int[] insertionSort(int[] a) {
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
    public static void quickSort(int[] arr, int left, int right) {

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

    public static int binarySearch(int arr[], int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;

            } else if (arr[mid] > key) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int recursiveBinarySearch(int arr[], int left, int right, int key) {
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

    public static List<Integer> performKMPSearch(String text, String pattern) {
        int[] compliedPatternArray = compilePatternArray(pattern);

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
                patternIndex = compliedPatternArray[patternIndex - 1];
            } else if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                if (patternIndex != 0)
                    patternIndex = compliedPatternArray[patternIndex - 1];
                else
                    textIndex = textIndex + 1;
            }
        }
        return foundIndexes;
    }

    public static int[] compilePatternArray(String pattern) {
        int patternLength = pattern.length();
        int len = 0;
        int i = 1;
        int[] compliedPatternArray = new int[patternLength];
        compliedPatternArray[0] = 0;

        while (i < patternLength) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                compliedPatternArray[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = compliedPatternArray[len - 1];
                } else {
                    compliedPatternArray[i] = len;
                    i++;
                }
            }
        }
        return compliedPatternArray;
    }

    public static int exponentialSearch(int[] integers, int elementToSearch) {
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

    public static int interpolationSearch(int[] integers, int elementToSearch) {

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

    // array has to be sorted
    public static int jumpSearch(int[] integers, int elementToSearch) {
        int arrayLength = integers.length;
        int jumpStep = (int) Math.sqrt(integers.length);
        int previousStep = 0;

        while (integers[Math.min(jumpStep, arrayLength) - 1] < elementToSearch) {
            previousStep = jumpStep;
            jumpStep += (int) (Math.sqrt(arrayLength));
            if (previousStep >= arrayLength)
                return -1;
        }
        while (integers[previousStep] < elementToSearch) {
            previousStep++;
            if (previousStep == Math.min(jumpStep, arrayLength))
                return -1;
        }

        if (integers[previousStep] == elementToSearch)
            return previousStep;
        return -1;
    }

    public static boolean isSorted(String[] strings) {
        for (int i = 1; i < strings.length; i++) {
            if (strings[i - 1].compareTo(strings[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    // Brute Force
    // O(n²) time complexity
    public static int findDuplicate(int[] numbers) {
        int result = -1;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    result = numbers[i];
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] lines = {"abc", "bcd", "cde"};
        boolean isSorted = isSorted(lines);
        System.out.println(isSorted);

        String pattern = "AAABAAA";
        String text = "ASBNSAAAAAABAAAAABAAAAAGAHUHDJKDDKSHAAJF";
        List<Integer> foundIndexes = performKMPSearch(text, pattern);

        if (foundIndexes.isEmpty()) {
            System.out.println("Pattern not found in the given text String");
        } else {
            System.out.println("Pattern found in the given text String at positions: "
                    + foundIndexes.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }

        int[] a = {9, 8, 13, 5, 34};

        int[] res = bubbleSort(a);
        System.out.println(Arrays.toString(res));

        res = selectionSort(a);
        System.out.println(Arrays.toString(res));

        res = insertionSort(a);
        System.out.println(Arrays.toString(res));

        int[] arrayForQuickSort = {9, 8, 13, 10, 5, 34};
        quickSort(arrayForQuickSort, 0, arrayForQuickSort.length - 1);
        System.out.println("quickSort: " + Arrays.toString(arrayForQuickSort));

        // Testing Binary Search
        int[] sortedArray = new int[]{21, 41, 31, 12, 623, 543, 731, 1898};
        Arrays.sort(sortedArray);
        int indexOfFoundValue = binarySearch(sortedArray, 731);
        System.out.println("indexOfFoundValue: " + indexOfFoundValue);

        int indexOfFoundValueRecursive = recursiveBinarySearch(sortedArray, 0, sortedArray.length, 731);
        System.out.println("indexOfFoundValueRecursive: " + indexOfFoundValueRecursive);
    }
}
