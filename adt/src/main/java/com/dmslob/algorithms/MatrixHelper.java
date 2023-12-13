package com.dmslob.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MatrixHelper {

	public List<Integer> find(Integer[][] matrix, Integer key) {
		int m, n;
		List<Integer> itemPositions = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			n = Arrays.binarySearch(matrix[i], key);
			m = i;
			if (n >= 0) {
				// found at row
				itemPositions.add(m);
				// found at column
				itemPositions.add(n);
				break;
			}
		}
		return itemPositions;
	}

	public void print(Integer[][] matrix, Integer key) {
		for (Integer[] ints : matrix) {
			for (int j = 0; j < matrix[0].length; j++) {
				Integer el = ints[j];
				if (Objects.equals(key, el)) {
					System.out.print("{" + el + "}" + (el < 10 ? "  " : " "));
				} else {
					System.out.print(el + (el < 10 ? "  " : " "));
				}
			}
			System.out.println();
		}
	}
}
