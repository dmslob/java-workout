package com.dmslob.algorithms;

public class IslandsCounter {

    public static void main(String[] args) {
        IslandsCounter islandsCounter = new IslandsCounter();

        int[][] islands = {
                {1, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 0, 1, 1}
        };

        int count = islandsCounter.count(islands);
        System.out.println(count);
    }

    public int count(int[][] islands) {
        if (islands == null || islands.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < islands.length; i++) {
            for (int j = 0; j < islands[0].length; j++) {
                if (islands[i][j] == 1) {
                    count++;
                    merge(islands, i, j);
                }
            }
        }
        return count;
    }

    private void merge(int[][] islands, int i, int j) {
        if (i < 0 || j < 0 || i > islands.length - 1 || j > islands[0].length - 1) {
            return;
        }
        // if current cell is water or visited
        if (islands[i][j] != 1) return;

        // set visited cell to '0'
        islands[i][j] = 0;
        // merge all adjacent land
        merge(islands, i - 1, j);
        merge(islands, i + 1, j);
        merge(islands, i, j - 1);
        merge(islands, i, j + 1);
    }
}
