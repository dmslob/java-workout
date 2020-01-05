package com.dmslob.oca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayMain {

    public static void main(String[] args) {
        //init();
        //usingMultidimensionalArray();
        //arrayListInit();
        //addingItems();
        equality();
    }

    static void indexOutOfBoundsException() {
        List<String> list = new ArrayList();
        System.out.println(list.size());
        list.remove(0);
    }

    static void init() {
        int[][] differentSize = {{1, 4}, {3}, {9, 8, 7}};
        int[][] arr = new int[4][];
        arr[0] = new int[5];
        arr[1] = new int[3];

        System.out.println(Arrays.toString(differentSize));

        //int[][] java = new int[][]; // compile error
        int[][] scores = new int[5][];
        Object[][][] cubbies = new Object[3][0][5];
        java.util.Date[] dates[] = new java.util.Date[2][];
        //int[][] types = new int[]; // compile error
        char[] c = new char[2];
    }

    static void usingMultidimensionalArray() {
        int[][] twoD = new int[3][2];
        for (int i = 0; i < twoD.length; i++) {
            for (int j = 0; j < twoD[i].length; j++)
                System.out.print(twoD[i][j] + " "); // print element
            System.out.println(); // time for a new row
        }
        System.out.println();
        for (int[] inner : twoD) {
            for (int num : inner)
                System.out.print(num + " ");
            System.out.println();
        }
    }

    static void arrayListInit() {
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList(10);
        ArrayList list3 = new ArrayList(list2);
        ArrayList<String> list4 = new ArrayList<String>();
        ArrayList<String> list5 = new ArrayList<>();
        List<String> list6 = new ArrayList<>();
        //ArrayList<String> list7 = new List<>(); // DOES NOT COMPILE

        list1.add("ert");
        list1.add(0, "rty");
        //list1.add(67, "");// IndexOutOfBoundsException.
        list1.add(true);

        System.out.println(list1.remove(0));
        //list1.remove(45); // IndexOutOfBoundsException.
    }

    static void addingItems() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add(1, "newOne");
        System.out.println(list.toString());
        list.set(1, "anotherOne");
        System.out.println(list.toString());
    }

    static void equality() {
        List<String> one = new ArrayList<>();
        List<String> two = new ArrayList<>();
        System.out.println(one.equals(two)); // true
        one.add("a"); // [a]
        for (String s : one) {
            one.remove(0);
        }

        System.out.println(one.equals(two)); // false
        two.add("a"); // [a]
        System.out.println(one.equals(two)); // true
        one.add("b"); // [a,b]
        two.add(0, "b"); // [b,a]
        System.out.println(one.equals(two)); // false
        System.out.println(one);
        two.set(0, "c");
        two.remove(0);
        System.out.println(two);

        int[] ints1 = new int[]{1, 2, 3};
        int[] ints2 = new int[]{1, 2, 3};
        System.out.println("ints1.equals(ints2) = " + ints1.equals(ints2));
        System.out.println("Arrays.equals(ints1, ints2) = " + Arrays.equals(ints1, ints2));

        int[] random = {6, -4, 12, 0, -10};
        int x = 12;
        int y = Arrays.binarySearch(random, x);
        System.out.println(y);

        List<Integer> list = Arrays.asList(10, 4, -1, 5);
        Collections.sort(list);
        Integer array[] = list.toArray(new Integer[4]);
        System.out.println(array[0]);

        List<String> hex = Arrays.asList("30", "8", "3A", "FF");
        Collections.sort(hex);
        System.out.println(hex.toString());
        int a = Collections.binarySearch(hex, "8");
        int b = Collections.binarySearch(hex, "3A");
        int c = Collections.binarySearch(hex, "4F");
        System.out.println(a + " " + b + " " + c);

        List<String> abc = Arrays.asList("g", "f", "a", "b");
        Collections.sort(abc);
        System.out.println(abc.toString());

        List<Integer> ages = new ArrayList<>();
        ages.add(Integer.parseInt("5"));
        ages.add(Integer.valueOf("6"));
        ages.add(7);
        ages.add(null);
        //for (int age : ages) System.out.print(age); // NPE

        List<String> on = new ArrayList<String>();
        on.add("abc");
        List<String> tw = new ArrayList<>();
        tw.add("abc");
        System.out.println(on.equals(tw));
    }
}
