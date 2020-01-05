package com.dmslob.oca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringMain {

    public static void main(String[] args) throws Exception {

        comparingStrings();

        //stringIsNull();
        //comparingStrings();
        //stringBuilder();
        //stringPlusBoolean();
        //stringBuilderMethods();
        //insert();
        //stringArray();
        //test2();

        Object[][][] cubbies = new Object[3][0][5];
        System.out.println(cubbies.length);

        ArrayList<Integer> values = new ArrayList<>();
        values.add(4);
        values.add(5);
        values.set(1, 6);
        System.out.println(values.toString());

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
        System.out.println(hex.toString()); // [30, 3A, 8, FF]
        int xx = Collections.binarySearch(hex, "8");
        int yy = Collections.binarySearch(hex, "3A");
        int zz = Collections.binarySearch(hex, "4F");
        int gg = Collections.binarySearch(hex, "3F");
        System.out.println(xx + " " + yy + " " + zz + " " + gg);

        List<Integer> ages = new ArrayList<>();
        ages.add(Integer.parseInt("5"));
        ages.add(Integer.valueOf("6"));
        ages.add(7);
        //ages.add(null); // NPE
        for (int age : ages) System.out.print(age);

        System.out.println();
        List<String> one = new ArrayList<String>();
        one.add("abc");
        List<String> two = new ArrayList<>();
        two.add("abc");
        if (one == two)
            System.out.println("A");
        else if (one.equals(two))
            System.out.println("B");
        else
            System.out.println("C");
    }

    static void stringBuffer() {
        StringBuffer stringBuffer1 = new StringBuffer("1");
        StringBuffer stringBuffer2 = stringBuffer1.append("2");
        System.out.println(stringBuffer1);
        System.out.println(stringBuffer2);
        System.out.println(stringBuffer1 == stringBuffer2);
    }

    static void test1() {
        int numFish = 4;
        String fishType = "tuna";

        //String anotherFish = numFish + 1; // compile error

        String s = "Hello";
        String t = new String(s);
        if ("Hello".equals(s)) System.out.println("one");
        if (t == s) System.out.println("two");
        if (t.equals(s)) System.out.println("three");
        if ("Hello" == s) System.out.println("four");
        if ("Hello" == t) System.out.println("five");

        StringBuilder java = new StringBuilder("Java");
        System.out.println(java.reverse());

    }

    static void test2() {
        StringBuilder sb = new StringBuilder();
        sb.append("aaa").insert(1, "bb").insert(4, "ccc");
        System.out.println(sb);

        String s1 = "java";
        StringBuilder s2 = new StringBuilder("java");
        // if (s1 == s2) System.out.print("1"); // compile error
        if (s1.equals(s2)) System.out.print("2");

        class Lion {
            void roar(String roar1, StringBuilder roar2) {
                roar1.concat("!!!");
                roar2.append("!!!");
            }
        }
        String roar1 = "roar";
        StringBuilder roar2 = new StringBuilder("roar");
        new Lion().roar(roar1, roar2);
        System.out.println(roar1 + " " + roar2);

        String letters = "abcdef";
        System.out.println(letters.length());
        System.out.println(letters.charAt(3));
        //System.out.println(letters.charAt(6));//java.lang.StringIndexOutOfBoundsException

        String numbers = "012345678";
        System.out.println(numbers.substring(1, 3));
        System.out.println(numbers.substring(7, 7));
        System.out.println(numbers.substring(7));

        String s = "purr";
        s.toUpperCase();
        s.trim();
        s.substring(1, 3);
        //s.substring(3, 1); // StringIndexOutOfBoundsException
        s += " two";
        System.out.println(s.length());

        String a = "";
        a += 2;
        a += 'c';
        a += false;
        if (a == "2cfalse") System.out.println("==");
        if (a.equals("2cfalse")) System.out.println("equals");

        StringBuilder numbers1 = new StringBuilder("0123456789");
        numbers1.delete(2, 8);
        numbers1.append("-").insert(2, "+");
        System.out.println(numbers1);
    }

    public static int m1() throws Exception {
        throw new Exception("Some Exception");
    }

    static void stringArray() {
        String[] strings = {"stringValue"};
        Object[] objects = strings;
        String[] againStrings = (String[]) objects;
        //againStrings[0] = new StringBuilder(); // DOES NOT COMPILE
        objects[0] = new StringBuilder(); // careful! java.lang.ArrayStoreException: java.lang.StringBuilder
    }

    static void stringIsNull() {
        String str = null;
        str = str + "1";

        System.out.println(str);
    }

    static void stringPlusBoolean() {
        String s = "line";
        s += true;
        System.out.println(s);
    }

    static void comparingStrings() {
        String s1 = "Java";
        String s2 = "Java";

        StringBuilder sb1 = new StringBuilder();
        sb1.append("Ja").append("va");

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(sb1.toString() == s1);//false, toString() return new String
        System.out.println(sb1.toString().equals(s1));

        String hello = "Hello", lo = "lo";
        System.out.print((hello == ("Hel" + "lo")) + " "); // true
        System.out.print((hello == ("Hel" + lo)) + " "); // false
        System.out.println(hello == ("Hel" + lo).intern()); // true

        String x = "Hello World";
        String z = " Hello World".trim();
        System.out.println(x == z); // false

        Tiger t1 = new Tiger();
        Tiger t2 = new Tiger();
        Tiger t3 = t1;
        System.out.println(t1 == t1);//true
        System.out.println(t1 == t2);//false
        System.out.println(t1 == t3);//true
        System.out.println(t2 == t3);//false
    }

    static void stringBuilder() {
        StringBuilder sb = new StringBuilder("start");
        sb.append("+middle"); // sb = "start+middle"
        StringBuilder same = sb.append("+end"); // "start+middle+end"
        System.out.println(sb);
        System.out.println(same);

        StringBuilder sb3 = new StringBuilder(10);
        System.out.println("lehgth: " + sb3.length());
        System.out.println("capacity: " + sb3.capacity());
    }

    static void stringBuilderMethods() {
        StringBuilder sb = new StringBuilder("animals");
        String sub = sb.substring(sb.indexOf("a"), sb.indexOf("al"));
        int len = sb.length();
        char ch = sb.charAt(6);
        System.out.println(sub + " " + len + " " + ch);
    }

    static void insert() {
        StringBuilder sb = new StringBuilder("animals");
        sb.insert(8, "-"); // StringIndexOutOfBoundsException
        sb.insert(7, "-"); // sb = animals-
        sb.insert(0, "-"); // sb = -animals-
        sb.insert(4, "-"); // sb = -ani-mals-
        System.out.println(sb);
        System.out.println(sb.reverse());
    }
}

class Tiger {
    String name;
}
