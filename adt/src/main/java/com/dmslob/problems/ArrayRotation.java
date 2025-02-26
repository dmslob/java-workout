package com.dmslob.problems;

public class ArrayRotation {

    public static void leftRotate(int[] arr, int d, int n) {
        for (int i = 0; i < d; i++) {
            int j, temp;
            temp = arr[0];
            for (j = 0; j < n - 1; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = temp;
        }
    }
}
