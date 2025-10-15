package com.dmslob.tasks.arrays;

import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PairSumTest {

    /**
     * Given an array of integers sorted in ascending order and a target value, return the indexes
     * of any pair of numbers in the array that sum to the target. The order of the indexes In the
     * result doesn't matter. If no pair is found, return an empty array.
     */
    List<Integer> pairSum(List<Integer> nums, Integer target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if (sum < target) {
                left += 1;
            } else if (sum > target) {
                right -= 1;
            } else {
                return List.of(left, right);
            }
        }
        return List.of();
    }

    @Test
    public void should_return_indices_when_invoke_pairSum() {
        // given
        var nums = List.of(-5, -2, 3, 4, 6);
        int target = 7;
        var expected = List.of(2, 3);
        // when
        var actual = pairSum(nums, target);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
