package com.dmslob.tasks.problems;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WordSearchTest {

    boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.isEmpty()) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean backtrack(char[][] board, String word, int row, int col, int index) {
        // Base case: we've matched all characters
        if (index == word.length()) {
            return true;
        }
        // Check boundaries
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        // Check if current cell matches the character we're looking for
        if (board[row][col] != word.charAt(index)) {
            return false;
        }
        // Mark the cell as visited by temporarily modifying it
        char temp = board[row][col];
        board[row][col] = '#';
        // Explore all 4 directions: up, down, left, right
        boolean found = backtrack(board, word, row - 1, col, index + 1) ||  // up
                backtrack(board, word, row + 1, col, index + 1) ||          // down
                backtrack(board, word, row, col - 1, index + 1) ||          // left
                backtrack(board, word, row, col + 1, index + 1);            // right
        // Restore the cell (backtrack)
        board[row][col] = temp;
        return found;
    }

    @Test
    public void should_test_word_search() {
        // given
        char[][] board1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        // when
        var abcced = exist(board1, "ABCCED");
        // then
        assertThat(abcced).isTrue();
        // when
        var see = exist(board1, "SEE");
        // then
        assertThat(see).isTrue();
        // when
        var abcb = exist(board1, "ABCB");
        // then
        assertThat(abcb).isFalse();
        // given
        char[][] board2 = {
                {'A', 'B'},
                {'C', 'D'}
        };
        // when
        var abcd = exist(board2, "ABCD");
        // then
        assertThat(abcd).isFalse();
    }
}