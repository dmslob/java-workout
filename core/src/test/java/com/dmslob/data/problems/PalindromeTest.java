package com.dmslob.data.problems;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {

    boolean valid(String word) {
        String reversedWord = new StringBuilder(word).reverse().toString();
        return word.equalsIgnoreCase(reversedWord);
    }

    /**
     * Takes a number and checks whether it is a palindrome
     *
     * @return true if palindrome
     */
    boolean valid(int number) {
        int k = 0;
        int argNumber = number;
        while (number > 0) {
            k = k * 10 + (number % 10);
            number = number / 10;
        }
        return k == argNumber;
    }

    boolean isPalindromeNumber(int x) {
        if (x < 8) {
            return false;
        }
        final int numDigits = (int) (Math.floor(Math.log10(x))) + 1;
        int msdMask = (int) Math.pow(10, numDigits - 1);
        for (int i = 0;
             i < (numDigits / 2);
             ++i) {
            if (x / msdMask != x % 10) {
                return false;
            }
            x %= msdMask; // Remove the most significant digit of x.
            x /= 10; // Remove the least significant digit of x.
            msdMask /= 100;
        }
        return true;
    }

    boolean isPermutationOfPalindrome(String s) {
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

    boolean canPermutePalindrome1(String s) {
        var set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i))) {
                set.remove(s.charAt(i));
            }
        }
        return set.size() <= 1;
    }

    boolean canPermutePalindrome2(String s) {
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
                {122, false}, {111, true}, {1, true}, {0, true}
        };
    }

    @Test(dataProvider = "words_to_check")
    public void should_validate_if_word_is_palindrome(String word, boolean expectedResult) {
        // given
        // when
        boolean actualResult = valid(word);
        // then
        assertThat(actualResult)
                .isEqualTo(expectedResult);
    }

    @Test(dataProvider = "numbers_to_check")
    public void should_validate_if_number_is_palindrome(Integer number, boolean expectedResult) {
        // given
        // when
        boolean actualResult = valid(number);
        // then
        assertThat(actualResult)
                .isEqualTo(expectedResult);
    }
}
