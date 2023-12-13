package com.dmslob.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// An anagram is formed by using exactly the same letters of the original word,
// but with a different arrangement.
public class AnagramHelper {

    private final Map<String, Set<String>> anagramsMap = new HashMap<>();

    private String getKeyWord(String word) {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);

        return new String(wordChars);
    }

    public List<Set<String>> find(List<String> words) {
        words.forEach(word -> {
            String wordKey = getKeyWord(word);
            if (anagramsMap.containsKey(wordKey)) {
                Set<String> wordSet = anagramsMap.get(wordKey);
                wordSet.add(word);
            } else {
                Set<String> wordSet = new HashSet<>();
                wordSet.add(word);
                anagramsMap.put(wordKey, wordSet);
            }
        });
        return new ArrayList<>(anagramsMap.values());
    }
}
