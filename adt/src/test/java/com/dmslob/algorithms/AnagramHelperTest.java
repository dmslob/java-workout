package com.dmslob.algorithms;

import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnagramHelperTest {

    @Test
    public void should_find_anagrams() {
        // given
        AnagramHelper anagramHelper = new AnagramHelper();
        List<String> words = List.of(
                "дура", "руда", "удар", "рост",
                "сорт", "трос", "торс", "автор",
                "отвар", "тавро", "товар", "debit card",
                "bad credit", "elbow", "below", "cat", "tac"
        );
        // when
        final List<Set<String>> anagrams = anagramHelper.find(words);
        // then lets see result
        anagrams.forEach(setOfWords -> log.info("{}", setOfWords));
    }
}
