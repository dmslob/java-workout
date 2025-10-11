package com.dmslob.tasks.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SecondLargestTaskTest {

    /**
     * Finds the second-largest element in an integer array in a single pass.
     * Assumes the array has at least two distinct elements.
     *
     * @param arr The input integer array.
     * @return The second-largest element, or Integer.MIN_VALUE if not found/possible.
     */
    public static int findSecondLargest(int[] arr) {
        // Handle edge cases: null or array with less than 2 elements
        if (arr == null || arr.length < 2) {
            System.out.println("Array must contain at least two elements.");
            return Integer.MIN_VALUE;
        }
        int firstLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        // Iterate through the array once (single pass)
        for (int element : arr) {
            if (element > firstLargest) {
                // Current element is the new firstLargest
                secondLargest = firstLargest; // Old firstLargest becomes the new second firstLargest
                firstLargest = element;
            } else if (element > secondLargest && element < firstLargest) {
                // Current element is between firstLargest and secondLargest
                secondLargest = element;
            }
            // If element is less than secondLargest, or equal to firstLargest/secondLargest, do nothing.
        }
        return secondLargest;
    }

    @DataProvider
    public static Object[][] test_data() {
        return new Object[][]{
                // numbers array, expected second-largest number
                {new int[]{10, 5, 20, 8, 15}, 15},
                {new int[]{50, 50, 50, 50}, Integer.MIN_VALUE},
                {new int[]{3}, Integer.MIN_VALUE},
        };
    }

    @Test(dataProvider = "test_data")
    public void should_find_second_largest_number(int[] data, int expected) {
        // when
        int actual = findSecondLargest(data);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}