package com.dmslob.tasks.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LcsTest {

    /**
     * Given an unsorted array of integers nums,
     * return the length of the longest consecutive elements sequence.
     * You must write an algorithm that runs in O(n) time.
     * Example 1:
     * Input: nums = [100,4,200,1,3,2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
     * Therefore, its length is 4.
     * <a href="https://leetcode.com/problems/longest-consecutive-sequence/description/">...</a>
     */
    int findLcs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num : set) {
            // only start if `num` is the beginning of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }

    @DataProvider
    public Object[][] input_dataProvider() {
        return new Object[][]{
                // array, expected
                {new int[]{100, 4, 200, 1, 3, 2}, 4},
                {new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9},
                {new int[]{1, 0, 1, 2}, 3}
        };
    }

    @Test(dataProvider = "input_dataProvider")
    public void should_find_lcs(int[] nums, int expected) {
        // when
        int result = findLcs(nums);
        // then
        assertThat(result).isEqualTo(expected);
    }
}
