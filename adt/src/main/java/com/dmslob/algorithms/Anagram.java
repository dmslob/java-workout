package com.dmslob.algorithms;

import java.util.*;

// An anagram is formed by using exactly the same letters of the original word, but with a different arrangement.
public class Anagram {

    private Map<String, ArrayList> anagramWordMap = new HashMap<>();

    private void wordSort(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            String currentKey = keyWord(currentWord);

            if (anagramWordMap.containsKey(currentKey)) {
                ArrayList<String> listWords = anagramWordMap.get(currentKey);
                if (!listWords.contains(currentWord)) {
                    listWords.add(currentWord);
                }
            } else {
                ArrayList<String> listWords = new ArrayList<>();
                listWords.add(currentWord);
                anagramWordMap.put(currentKey, listWords);
            }
        }
    }

    private String keyWord(String wordParam) {
        char[] wordChars = wordParam.toCharArray();
        Arrays.sort(wordChars);

        return new String(wordChars);
    }

    private void displayResult() {
        Set<String> keys = anagramWordMap.keySet();
        TreeSet<String> sortedKeys = new TreeSet<>(keys);

        System.out.println("\nMap contains: ");
        System.out.println("Key\t\tValue");

        for (String key : sortedKeys) {
            System.out.printf("%-10s%10s\n", key, anagramWordMap.get(key));
        }
    }

    public static void main(String[] args) {
        String[] words = {"дура", "руда", "удар", "рост", "сорт", "трос",
                "торс", "автор", "отвар", "тавро", "товар"};

        String[] newWords = {"debit card", "bad credit", "elbow", "below", "cat", "tac"};

        Anagram anagram = new Anagram();
        anagram.wordSort(words);
        anagram.displayResult();

        System.out.println();
        Anagram newAnagram = new Anagram();
        newAnagram.wordSort(newWords);
        newAnagram.displayResult();
    }
}
