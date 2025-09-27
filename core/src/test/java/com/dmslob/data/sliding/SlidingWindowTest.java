package com.dmslob.data.sliding;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Sliding Window Technique is a method used to solve problems that involve subarray or substring or window.
 * - Instead of repeatedly iterating over the same elements,
 * the sliding window maintains a range (or "window") that moves step-by-step through the data,
 * updating results incrementally.
 * - The main idea is to use the results of previous window to do computations for the next window.
 * - Commonly used for problems like finding sub-arrays with a specific sum,
 * finding the longest substring with unique characters, or solving problems that require a fixed-size window
 * to process elements efficiently.
 * Example:
 * Given an array of integers arr[] and a number k. Return the maximum sum of a subarray of size k.
 * Note: A subarray is a contiguous part of any given array.
 * Input:   arr[] = [5, 2, -1, 0, 3], k = 3
 * Output:  6
 * Explanation: We get maximum sum by considering the subarray [5, 2 , -1]
 * Input:   arr[] = [1, 4, 2, 10, 23, 3, 1, 0, 20], k = 4
 * Output:  39
 * Explanation: We get maximum sum by adding subarray [4, 2, 10, 23] of size 4.
 */
public class SlidingWindowTest {

    @DataProvider
    public Object[][] input_dataProvider() {
        return new Object[][]{
                // array, k, expectedSum
                {new int[]{5, 2, -1, 0, 3}, 3, 6},
                {new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 4, 39},
                {new int[]{1, 4, 2}, 4, -1},
                {new int[]{1, 4, 2, 10}, 4, -1}
        };
    }

    /**
     * Brute force solution
     */
    int maxSumNaive(int[] arr, int n, int k) {
        if (n <= k) return -1;
        // Initialize result
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n - k + 1; i++) {
            int currentSum = 0;
            for (int j = 0; j < k; j++) {
                currentSum = currentSum + arr[i + j];
            }
            // Update result if required.
            maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;
    }


    @Test(dataProvider = "input_dataProvider")
    public void should_test_maxSumNaive(int[] nums, int k, int expectedSum) {
        // when
        int actualSum = maxSumNaive(nums, nums.length, k);
        // then
        assertThat(actualSum).isEqualTo(expectedSum);
    }

    int maxSum(int[] arr, int n, int k) {
        if (n <= k) return -1;
        // Compute sum of first window of size k
        int max_sum = 0;
        for (int i = 0; i < k; i++)
            max_sum += arr[i];
        // Compute sums of remaining windows by removing first element of previous
        // window and adding last element of current window.
        int window_sum = max_sum;
        for (int i = k; i < n; i++) {
            window_sum += arr[i] - arr[i - k];
            max_sum = Math.max(max_sum, window_sum);
        }
        return max_sum;
    }

    @Test(dataProvider = "input_dataProvider")
    public void should_test_maxSum(int[] nums, int k, int expectedSum) {
        // when
        int actualSum = maxSum(nums, nums.length, k);
        // then
        assertThat(actualSum).isEqualTo(expectedSum);
    }
}
