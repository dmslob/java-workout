package com.dmslob.tasks.problems;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IslandCounterTest {
	private final IslandCounter islandsCounter = new IslandCounter();

	@Test
	public void should_count_islands() {
		// given
		int expectedQuantity = 2;
		int[][] matrix = {
				{1, 0, 0, 0},
				{1, 0, 1, 0},
				{0, 0, 1, 1}
		};

		// when
		int count = islandsCounter.count(matrix);

		// then
		assertThat(count).isEqualTo(expectedQuantity);
	}
}
