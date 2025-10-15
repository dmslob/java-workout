package com.dmslob.tasks.problems;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {

	boolean textIsPalindrome(String text) {
		// Remove spaces, commas, and dots, and convert to lowercase
		String cleaned = text.replaceAll("[\\s,.]", "").toLowerCase();
		String reversed = new StringBuilder(cleaned).reverse().toString();
		return cleaned.equalsIgnoreCase(reversed);
	}

	boolean textIsPalindrome_2Ptr(String input) {
		int left = 0;
		int right = input.length() - 1;
		while (left < right) {
			// Skip non-letter characters
			while (left < right && !Character.isLetter(input.charAt(left))) {
				left++;
			}
			while (left < right && !Character.isLetter(input.charAt(right))) {
				right--;
			}
			// Compare characters case-insensitively
			if (Character.toLowerCase(input.charAt(left)) != Character.toLowerCase(input.charAt(right))) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	/**
	 * Takes a number and checks whether it is a palindrome
	 *
	 * @return true if palindrome
	 */
	boolean textIsPalindrome(int number) {
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
	public static Object[][] text_dataProvider() {
		return new Object[][] {
				{"kayak", true}, {"madam", true}, {"reviver", true},
				{"racecar", true}, {"refer", true}, {"bob", true},
				{"A man, a plan, a canal, Panama", true},
				{"race", false}, {"hope", false}, {"ford", false}
		};
	}

	@Test(dataProvider = "text_dataProvider")
	public void should_validate_if_string_is_palindrome(String text, boolean expectedResult) {
		// when
		boolean actualResult = textIsPalindrome(text);
		// then
		assertThat(actualResult)
				.isEqualTo(expectedResult);
		// or
		actualResult = textIsPalindrome_2Ptr(text);
		// then
		assertThat(actualResult)
				.isEqualTo(expectedResult);
	}

	@DataProvider
	public static Object[][] numbers_to_check() {
		return new Object[][] {
				{1221, true}, {1001, true}, {100001, true}, {541145, true},
				{122, false}, {111, true}, {1, true}, {0, true}
		};
	}

	@Test(dataProvider = "numbers_to_check")
	public void should_validate_if_number_is_palindrome(Integer number, boolean expectedResult) {
		// when
		boolean actualResult = textIsPalindrome(number);
		// then
		assertThat(actualResult)
				.isEqualTo(expectedResult);
	}
}
