package com.dmslob.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Palindrome {

    public boolean valid(String word) {
        String reversedWord = new StringBuilder(word).reverse().toString();
        return word.equalsIgnoreCase(reversedWord);
    }

    /**
     * Takes a number and checks whether it is a palindrome
     *
     * @return true if palindrome
     */
    public boolean valid(int number) {
        int k = 0;
        int argNumber = number;
        while (number > 0) {
            k = k * 10 + (number % 10);
            number = number / 10;
        }
        return k == argNumber;
    }

    public boolean isPermutationOfPalindrome(String s) {
        var table = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i)]++;
            if (table[s.charAt(i)] % 2 == 0) {
                count--;
            } else {
                count++;
            }
        }

        return count <= 1;
    }

    public boolean canPermutePalindrome1(String s) {
        var set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i))) {
                set.remove(s.charAt(i));
            }
        }
        return set.size() <= 1;
    }

    public boolean canPermutePalindrome2(String s) {
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int count = 0;
        for (char key : map.keySet()) {
            count += map.get(key) % 2;
        }
        return count <= 1;
    }
}
