package com.dmslob.data.problems;

public class IslandCounter {

	public int count(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int quantity = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					quantity++;
					merge(matrix, i, j);
				}
			}
		}
		return quantity;
	}

	private void merge(int[][] matrix, int i, int j) {
		if (i < 0 || j < 0
				|| i > matrix.length - 1
				|| j > matrix[0].length - 1
		) {
			return;
		}
		// if current cell is water or visited
		if (matrix[i][j] != 1) {
			return;
		}
		// set visited cell to '0'
		matrix[i][j] = 0;
		// merge all adjacent land
		merge(matrix, i - 1, j);
		merge(matrix, i + 1, j);
		merge(matrix, i, j - 1);
		merge(matrix, i, j + 1);
	}
}
