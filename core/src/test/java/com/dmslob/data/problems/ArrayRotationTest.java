package com.dmslob.data.problems;

import org.testng.annotations.Test;

public class ArrayRotationTest {
    @Test
    public void should_rotate_array() {
        // given
        int[] arr = {1, 2, 3, 4, 5};
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
        // when
        // Calling method to rotate array leftwards
        // and later printing the array elements
        ArrayRotation.leftRotate(arr, 2, arr.length);
        // then
        for (int j : arr) System.out.print(j + " ");
    }
}