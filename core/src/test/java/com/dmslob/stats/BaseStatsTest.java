package com.dmslob.stats;

import java.util.Arrays;

import org.testng.annotations.Test;

public class BaseStatsTest {

	@Test
	public void should_calculate_standard_deviation() {
		// given
		double[] nums = {25, 5, 45, 68, 61, 46, 24, 95};
		System.out.println("List of elements: " + Arrays.toString(nums));
		// when
		double standardDeviation = std(nums);
		// then
		System.out.format("Standard Deviation = %.6f", standardDeviation);
	}

	// calculate standard deviation
	private double std(double[] nums) {
		// get the sum of nums
		double sum = 0.0;
		for (double i : nums) {
			sum += i;
		}
		// get the mean of nums
		int len = nums.length;
		double mean = sum / len;
		// calculate the standard deviation
		double standardDeviation = 0.0;
		for (double num : nums) {
			standardDeviation += Math.pow(num - mean, 2);
		}
		return Math.sqrt(standardDeviation / len);
	}
}
