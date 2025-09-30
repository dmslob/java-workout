package com.dmslob.tasks.problems;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatrixDiagonalSumTest {

    /**
     * Given a square matrix mat, return the sum of the matrix diagonals.
     * Only include the sum of all the elements on the primary diagonal
     * and all the elements on the secondary diagonal that are not part of the primary diagonal.
     * leetcode problem: @see <a href="https://leetcode.com/problems/matrix-diagonal-sum/description/">matrix-diagonal-sum</a>
     */
    int diagonalSum(int[][] mat) {
        int n = mat.length; // since it's a square matrix
        int sum = 0;
        for (int i = 0; i < n; i++) {
            // Add primary diagonal element
            sum += mat[i][i];
            // Add secondary diagonal element
            sum += mat[i][n - 1 - i];
        }
        // If the matrix has an odd size, the center element was counted twice
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }
        return sum;
    }

    @Test
    public void should_calculate_diagonal_sum() {
        // given
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int expectedSum = 25;
        // when
        int sum = diagonalSum(mat);
        // then
        // Output (1+5+9 + 3+5+7 - 5) = 25
        assertThat(sum).isEqualTo(expectedSum);

        // given
        mat = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        expectedSum = 8;
        // when
        sum = diagonalSum(mat);
        // then
        // Output (1+1+1+1 + 1+1+1+1) = 8
        assertThat(sum).isEqualTo(expectedSum);

        // given
        mat = new int[][]{{5}};
        expectedSum = 5;
        // when
        sum = diagonalSum(mat);
        // then
        assertThat(sum).isEqualTo(expectedSum);
    }
}
