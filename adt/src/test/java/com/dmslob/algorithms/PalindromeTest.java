package com.dmslob.algorithms;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {

    private final Palindrome palindrome = new Palindrome();

    @DataProvider
    public static Object[][] words_to_check() {
        return new Object[][]{
                {"kayak", true}, {"madam", true}, {"reviver", true},
                {"racecar", true}, {"refer", true}, {"bob", true},
                {"race", false}, {"hope", false}, {"ford", false}
        };
    }

    @DataProvider
    public static Object[][] numbers_to_check() {
        return new Object[][]{
                {1221, true}, {1001, true}, {100001, true}, {541145, true},
                {122, false}, {111, false}, {1, false}, {0, false}
        };
    }

    @Test(dataProvider = "words_to_check")
    public void should_validate_if_word_is_palindrome(String word, boolean expectedResult) {
        // given
        // when
        boolean actualResult = palindrome.valid(word);
        // then
        assertThat(actualResult)
                .isEqualTo(expectedResult);
    }

    @Test(dataProvider = "numbers_to_check")
    public void should_validate_if_number_is_palindrome(Integer number, boolean expectedResult) {
        // given
        // when
        boolean actualResult = palindrome.valid(number);
        // then
        assertThat(actualResult)
                .isEqualTo(expectedResult);
    }
}
