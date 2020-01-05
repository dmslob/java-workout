package com.dmslob.algorithms;

import java.util.Arrays;

public class PermutationFinder {

    private int[] numbers;

    public PermutationFinder(int[] numbers) {
        this.numbers = numbers;
    }

    public static void main(String[] args) {
        int[] numbers = {3, 4, 5};
        PermutationFinder permutationFinder = new PermutationFinder(numbers);

        System.out.println(Arrays.toString(numbers));
        permutationFinder.permutation();
    }

    public void permutation() {
        int length = numbers.length;
        for (int i = length - 1; i > 0; i--) {
            if (numbers[i - 1] < numbers[i]) {
                for (int j = length - 1; j > 0; j--) {
                    if (numbers[j] > numbers[i - 1]) {
                        int q = numbers[i - 1];
                        numbers[i - 1] = numbers[j];
                        numbers[j] = q;
                        System.out.println(Arrays.toString(numbers));
                    }
                }
            }
        }
    }
}
