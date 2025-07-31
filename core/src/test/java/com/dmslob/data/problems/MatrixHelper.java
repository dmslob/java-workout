package com.dmslob.data.problems;

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

    /*
        To transpose a matrix means to create a new matrix by swapping the rows and columns of the original matrix.
        if you have a matrix, its transpose is formed by taking the first row of the original matrix
        and making it the first column of the new matrix, the second row becomes the second column, and so on
     */
    static int[][] transposeMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0][0];
        }
        int maxCols = 0;
        for (int[] row : matrix) {
            if (row != null && row.length > maxCols) {
                maxCols = row.length;
            }
        }
        int rowCount = matrix.length;
        int[][] transposed = new int[maxCols][rowCount];
        for (int i = 0; i < rowCount; i++) {
            int[] row = matrix[i];
            for (int j = 0; j < maxCols; j++) {
                if (row != null && j < row.length) {
                    transposed[j][i] = row[j];
                } else {
                    transposed[j][i] = 0; // Default value for missing elements
                }
            }
        }
        return transposed;
    }

    // Helper method to print a matrix
    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
