package com.dmslob.data.arrays;

import org.testng.annotations.Test;

public class ArrayRotationTest {
    
    static void leftRotate(int[] arr, int d, int n) {
        for (int i = 0; i < d; i++) {
            int j, temp;
            temp = arr[0];
            for (j = 0; j < n - 1; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = temp;
        }
    }

    @Test
    public void should_rotate_array() {
        // given
        int[] arr = {1, 2, 3, 4, 5};
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
        // when
        // Calling method to rotate array leftwards
        // and later printing the array elements
        leftRotate(arr, 2, arr.length);
        // then
        for (int j : arr) System.out.print(j + " ");
    }
}