package com.dmslob.collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class CountPairsTest {
	/*
	 * 	Given an array of integers, and a number ‘sum’,
	 * 	find the number of pairs of integers in the array whose sum is equal to ‘sum’.
	 * 	Examples:
	 * 	Input:  arr[] = {1, 5, 7, -1}, sum = 6
	 * 	Output:  2
	 *	Explanation: Pairs with sum 6 are (1, 5) and (7, -1)
	 */

	@Test
	public void should_count_pairs_by_foreach() {
		// given
		int[] numbers = {1, 5, 7, -1, 5};
		int sum = 6;
		int expectedCountNumber = 3;
		// when
		int countNumber = countByForEach(numbers, sum);
		// then
		assertThat(countNumber).isEqualTo(expectedCountNumber);
	}

	@Test
	public void should_count_pairs_by_map() {
		// given
		int[] numbers = {1, 5, 7, -1, 5};
		int sum = 6;
		int expectedCountNumber = 3;
		// when
		int countNumber = countByMap(numbers, sum);
		// then
		assertThat(countNumber).isEqualTo(expectedCountNumber);
	}

	public static int countByForEach(int[] arr, int sum) {
		int count = 0;
		// Consider all possible pairs and check their sums
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if ((arr[i] + arr[j]) == sum) {
					count++;
				}
			}
		}
		return count;
	}

	public static int countByMap(int[] numbers,  int sum) {
		Map<Integer, Integer> m = new HashMap<>();
		int count = 0;
		for (int number : numbers) {
			if (m.containsKey(sum - number)) {
				count += m.get(sum - number);
			}
			if (m.containsKey(number)) {
				m.put(number, m.get(number) + 1);
			} else {
				m.put(number, 1);
			}
		}
		return count;
	}
}
