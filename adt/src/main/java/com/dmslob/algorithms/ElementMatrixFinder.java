package com.dmslob.algorithms;

import java.util.Arrays;

public class ElementMatrixFinder {

    public static void main(String[] args) {
        ElementMatrixFinder matrixFinder = new ElementMatrixFinder();
        int keyToFind = 12;

        int[][] matrix = matrixFinder.fill(5, 5);
        matrixFinder.show(matrix);

        System.out.println();
        matrixFinder.find(matrix, keyToFind);
    }

    public void find(int[][] matrix, int key) {
        int m, n = 0;
        for (int i = 0; i < matrix.length; i++) {
            n = Arrays.binarySearch(matrix[i], key);
            m = i;
            if (n >= 0) {
                System.out.println("Found at row = " + m + " col = " + n);
                break;
            }
        }
        if (n < 0) System.out.println("Element not found");
    }

    public void show(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < 10) {
                    System.out.print(matrix[i][j] + "  ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Creates and fills the matrix
     */
    public int[][] fill(int x, int y) {
        int count = 0;
        int[][] matrix = new int[x][y];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = count + 1;
                count++;
            }
        }
        return matrix;
    }
}
