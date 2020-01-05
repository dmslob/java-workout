package com.dmslob.collection.arrays;

import java.util.Arrays;

public class MatrixOperation {

    public static void main(String[] args) {

        int matrix[][] = {
                {30, 10, 20, 40},
                {35, 45, 15, 25},
                {37, 29, 27, 48},
                {45, 39, 33, 50}
        };

        sort(matrix);
        search(matrix, 29);
        bsearch(matrix, 29);

        testMatrixMultiplication();
    }

    private static void sort(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            Arrays.sort(matrix[i]);
        }
    }

    private static void search(int[][] mat, int x) {
        int n = mat.length;
        int i = 0, j = n - 1; //set indexes for top right element
        while (i < n && j >= 0) {
            if (mat[i][j] == x) {
                System.out.println("Element is Found at point: " + i + ", " + j);
                return;
            }
            if (mat[i][j] > x)
                j--;
            else // if mat[i][j] < x
                i++;
        }

        System.out.println("Element is not found");
    }

    private static void bsearch(int[][] mat, int x) {
        for (int i = 0; i < mat.length; i++) {
            int n = Arrays.binarySearch(mat[i], x);
            if (n >= 0) {
                System.out.println("Element is found at point: " + i + ", " + n);
            }
        }
    }

    private static void testMatrixMultiplication() {
        int[][] a = {{4, 3}, {2, 1}};
        int[][] b = {{5, 1}, {1, 2}};

        int[][] result = multiplication(a, b);

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++)
                System.out.print(result[i][j] + " ");
            System.out.println();
        }
    }

    private static int[][] multiplication(int[][] a, int[][] b) {

        int aRows = a.length;
        int aColumns = a[0].length;
        int bRows = b.length;
        int bColumns = b[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        int[][] c = new int[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                c[i][j] = 0;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;
    }
}
