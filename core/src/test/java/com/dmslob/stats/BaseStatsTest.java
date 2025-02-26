package com.dmslob.stats;

import com.google.common.math.Stats;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.OptionalDouble;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaseStatsTest {

	@Test
	public void should_calculate_mean() {
	    // given
		double[] nums = {25, 5, 45, 68, 61, 46, 24, 95};
		double expected = 46.125d;
	    // when
		OptionalDouble mean = Arrays.stream(nums).average();
	    // then
		assertThat(mean).isEqualTo(OptionalDouble.of(expected));
		// or
		// given
		var testStat = Stats.of(nums);
		// when
		double meanByGuava = testStat.mean();
		// then
		assertThat(meanByGuava).isEqualTo(expected);
	}

	@Test
	public void should_calculate_standard_deviation() {
		// given
		double[] nums = {25, 5, 45, 68, 61, 46, 24, 95};
		double expected = 26.732178642976333d;
		// when
		double standardDeviation = std(nums);
		// then
		assertThat(standardDeviation).isEqualTo(expected);
		// or
		// given
		var testStat = Stats.of(nums);
		// when
		double stdByGuava = testStat.populationStandardDeviation();
		// then
		assertThat(stdByGuava).isEqualTo(expected);
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
