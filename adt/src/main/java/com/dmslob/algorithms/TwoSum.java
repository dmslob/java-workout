package com.dmslob.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum {

    static int[] findTwoSum(int[] numbers, int sum) {
        int i = 0;
        int j = numbers.length - 1;
        List<Integer> indices = new ArrayList<>();

        do {
            if (numbers[i] + numbers[j] < sum)
                i++;
            else if (numbers[i] + numbers[j] > sum)
                j--;
            else {
                indices.add(i);
                indices.add(j);
                break;
            }
        } while (i < j);

        return indices.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[]{3, 1, 5, 7, 5, 9}, 10);
        if (indices != null) {
            System.out.println(Arrays.toString(indices));
        }
    }
}
