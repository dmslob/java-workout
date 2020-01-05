package com.dmslob.oca;

public class StringTest {

    public static void main(String[] args) {
        String name1 = "Fluffy";
        String name2 = new String("Fluffy");

        System.out.println(name1 == name2);

        String string = "animals";
        System.out.println(string.length()); // 7
        System.out.println(string.charAt(0)); // a
        System.out.println(string.charAt(6)); // s
        //System.out.println(string.charAt(7)); // throws java.lang.StringIndexOutOfBoundsException

        System.out.println(string.indexOf('a')); // 0
        System.out.println(string.indexOf("al")); // 4
        System.out.println(string.indexOf('a', 4)); // 4
        System.out.println(string.indexOf("al", 5)); // -1

        System.out.println(string.substring(3)); // mals
        System.out.println(string.substring(string.indexOf('m'))); // mals
        System.out.println(string.substring(3, 4)); // m
        System.out.println(string.substring(3, 7)); // mals

        System.out.println(string.substring(3, 3)); // empty string
        //System.out.println(string.substring(3, 2)); // throws exception
        //System.out.println(string.substring(3, 8)); // throws exception

        String string2 = "animals";
        System.out.println(string2.toUpperCase()); // ANIMALS
        System.out.println("Abc123".toLowerCase()); // abc123

        System.out.println("abc".equals("ABC")); // false
        System.out.println("ABC".equals("ABC")); // true
        System.out.println("abc".equalsIgnoreCase("ABC")); // true
        System.out.println("ABC".equalsIgnoreCase("abc")); // true

        System.out.println("abc".contains("b")); // true
        System.out.println("abc".contains("B")); // false

        System.out.println("abcabc".replace('a', 'A')); // AbcAbc
        System.out.println("abcabc".replace("a", "A")); // AbcAbc

        System.out.println("abc".trim()); // abc
        System.out.println("\t a b c\n".trim()); // a b c

        String start = "AniMaL ";
        String trimmed = start.trim();
        System.out.println(trimmed); // "AniMaL"

        String lowercase = trimmed.toLowerCase(); // "animal"
        System.out.println(lowercase);

        String result = lowercase.replace('a', 'A'); // "AnimAl"
        System.out.println(result);

    }
}
