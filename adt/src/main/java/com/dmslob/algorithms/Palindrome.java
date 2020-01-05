package com.dmslob.algorithms;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(Palindrome.isPalindrome("Deleveled"));
        System.out.println(Palindrome.isPalindrome("Dodod"));
    }

    static boolean isPalindrome(String word) {
        String reversedWord = new StringBuilder(word).reverse().toString();
        return (word.equalsIgnoreCase(reversedWord)) ? true : false;
    }
}
