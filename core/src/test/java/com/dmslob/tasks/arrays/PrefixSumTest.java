package com.dmslob.tasks.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrefixSumTest {

    /**
     * Finds the number of sub-arrays that sum to target K using the
     * Prefix Sum and HashMap technique.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    int subarraySum(int[] A, int K) {
        int count = 0;
        int currentSum = 0;
        // Map to store {PrefixSum : Frequency of that sum}
        Map<Integer, Integer> sumFreq = new HashMap<>();
        // Base case: A prefix sum of 0 exists once (before index 0)
        sumFreq.put(0, 1);
        for (int num : A) {
            currentSum += num;
            // Target Complement: The required prefix sum that came before
            // If an array ends at current index (j) with sum S, and we need K,
            // we look for a previous prefix sum (i-1) equal to (S - K).
            int targetComplement = currentSum - K;
            if (sumFreq.containsKey(targetComplement)) {
                // If the complement exists, every time it appeared,
                // it marks the start of a new subarray that sums to K.
                count += sumFreq.get(targetComplement);
            }
            // Update the map with the current prefix sum frequency
            sumFreq.put(currentSum, sumFreq.getOrDefault(currentSum, 0) + 1);
        }
        return count;
    }

    @DataProvider
    public Object[][] input_dataProvider() {
        return new Object[][]{
                // array, k, expected
                // [1, 2, 3, 4, 5] -> Sub-arrays: [3, 4] (sum=7) -> Count is 1.
                {new int[] {1, 2, 3, 4, 5}, 7, 1},
                // Sub-arrays summing to 2: [1, 1] (index 0 to 1), [1, 1] (index 1 to 2)
                {new int[] {1, 1, 1}, 2, 2},
                // Sub-arrays summing to 0: [1, -1], [0], [1, -1, 0]
                {new int[] {1, 2, 3, 4, 5}, 7, 1}
        };
    }

    @Test(dataProvider = "input_dataProvider")
    public void should_test_subarray_sum(int[] A, int K, int expected) {
        // given
        int actual = subarraySum(A, K);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
