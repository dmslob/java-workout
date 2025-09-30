package com.dmslob.tasks.problems;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoveZerosTest {

    /**
     * Given an integer array nums, move all 0's to the end of it
     * while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.
     * leetcode problem: @see <a href="https://leetcode.com/problems/move-zeroes/">move-zeroes</a>
     */
    void moveZeroes(int[] nums) {
        int insertPos = 0;
        // First pass: move all non-zero elements forward
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
        // Second pass: fill the rest with zeros
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    @Test
    public void should_move_zeros_to_the_end() {
        // given
        int[] nums = {0, 1, 0, 3, 12};
        int[] expected = {1, 3, 12, 0, 0};
        // when
        moveZeroes(nums);
        // then
        assertThat(nums).isEqualTo(expected);

        // given
        nums = new int[]{0};
        expected = new int[]{0};
        // when
        moveZeroes(nums);
        // then
        assertThat(nums).isEqualTo(expected);
    }
}
