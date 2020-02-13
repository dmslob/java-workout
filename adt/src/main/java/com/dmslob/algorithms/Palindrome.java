package com.dmslob.algorithms;

public class Palindrome {

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();

        System.out.println(palindrome.isPalindrome("Deleveled"));
        System.out.println(palindrome.isPalindrome("Dodod"));
    }

    boolean isPalindrome(String word) {
        String reversedWord = new StringBuilder(word).reverse().toString();
        return (word.equalsIgnoreCase(reversedWord)) ? true : false;
    }
}
