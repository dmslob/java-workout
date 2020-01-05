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
