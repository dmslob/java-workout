package com.dmslob.collection.arrays;

import java.util.Arrays;

public class ArrayDeclarations {

    int[][] a() {
        return new int[0][];
    }

    int[] b()[] {
        return new int[0][];
    }

    int c()[][] {
        return new int[0][];
    }

    void arraysComparison() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        boolean isEquals = Arrays.equals(a, b);
        System.out.println(isEquals);
    }

    public static void main(String[] args) {
        ArrayDeclarations arrayDeclarations = new ArrayDeclarations();

        int[][] a = arrayDeclarations.a();
        int[][] b = arrayDeclarations.b();
        int[][] c = arrayDeclarations.c();

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));

        int[] aa = new int[]{1, 2, 3};
        int[] bb = new int[]{1, 2, 3};
        System.out.println(aa.equals(bb)); //prints "false" because a and b refer to different objects
        System.out.println(Arrays.equals(aa, bb)); //prints "true" because the elements of a and b have the
    }
}
