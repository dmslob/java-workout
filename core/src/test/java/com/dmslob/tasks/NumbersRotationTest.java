package com.dmslob.tasks;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumbersRotationTest {
    // Function to check if a number remains the same after 180-degree rotation
    static boolean isSame(int input) {
        var dict = Map.of(
                0, 0,
                1, 1,
                6, 9,
                8, 8,
                9, 6);
        int reverse = 0;
        int orig = input;
        while (input > 0) {
            int rightMost = input % 10;
            if (!dict.containsKey(rightMost)) {
                return false;
            }
            reverse *= 10;
            reverse += dict.get(rightMost);
            input /= 10;

        }
        return reverse == orig;
    }

    @DataProvider
    public static Object[][] numbers_to_test() {
        return new Object[][]{
                {609, true}, {16891, true}, {100, false}
        };
    }

    @Test(dataProvider = "numbers_to_test")
    public void should_verify_numbers_rotation(int input, boolean expected) {
        // when
        var isSameNumbers = isSame(input);
        // then
        assertThat(isSameNumbers).isEqualTo(expected);
    }
}
