package com.dmslob.algorithms;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import java.util.Arrays;

public class MatrixHelperTest {

	private final MatrixHelper matrixHelper = new MatrixHelper();

	@Test
	public void should_return_positions_when_item_is_found() {
		// given
		Integer keyToSearch = 12;
		Integer[][] matrix = buildMatrix(5, 5);
		matrixHelper.print(matrix, keyToSearch);

		// when
		var itemPositions = matrixHelper.find(matrix, keyToSearch);

		// then
		assertThat(itemPositions)
				.isNotEmpty()
				.hasSize(2);

		System.out.printf("%nFound at row = %d and col = %d",
				itemPositions.get(0), itemPositions.get(1));
	}

	@Test
	public void should_return_empty_when_item_is_not_found() {
		// given
		Integer keyToSearch = 30;
		Integer[][] matrix = buildMatrix(5, 5);
		matrixHelper.print(matrix, keyToSearch);

		// when
		var itemPositions = matrixHelper.find(matrix, keyToSearch);

		// then
		assertThat(itemPositions).isEmpty();
	}

	private Integer[][] buildMatrix(Integer x, Integer y) {
		int count = 0;
		Integer[][] matrix = new Integer[x][y];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = count + 1;
				count++;
			}
		}
		return matrix;
	}
}
