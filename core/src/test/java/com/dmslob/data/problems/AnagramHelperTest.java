package com.dmslob.data.problems;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class AnagramHelperTest {

	// An anagram is formed by using exactly the same letters of the original word,
	// but with a different arrangement.
	private final Map<String, Set<String>> anagramsMap = new HashMap<>();

	String getKeyWord(String word) {
		char[] wordChars = word.toCharArray();
		Arrays.sort(wordChars);
		return new String(wordChars);
	}

	List<Set<String>> find(List<String> words) {
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

	@Test
	public void should_find_anagrams() {
		// given
		List<String> words = List.of(
				"car", "arc", "angel", "glean", "debit card",
				"bad credit", "elbow", "below", "cat", "tac");
		// when
		final List<Set<String>> anagrams = find(words);
		// then
		assertThat(anagrams).isNotNull();
		assertThat(anagrams.get(0)).isEqualTo(Set.of("arc", "car"));
		assertThat(anagrams.get(1)).isEqualTo(Set.of("glean", "angel"));
	}
}
