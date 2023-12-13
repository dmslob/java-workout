package com.dmslob.types;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BooleanTest {
    @DataProvider
    public static Object[][] test_cases_and_expected() {
        return new Object[][]{
                {new boolean[]{true, true, true}, true},
                {new boolean[]{true, true, false}, true},
                {new boolean[]{true, false, false}, false},
                {new boolean[]{false, false, false}, false}
        };
    }

    @Test
    public void should_parse_null() {
        // given
        String stringFlag = null;
        // when
        boolean actualResult = Boolean.parseBoolean(stringFlag);
        // then
        Assert.assertFalse(actualResult);
    }

    @Test(dataProvider = "test_cases_and_expected")
    public void should_check_two_true_booleans_by_loop(boolean[] booleans, boolean expected) {
        // given
        int expectedTrueBooleans = 2;
        // when
        boolean result = ThreeBooleans.xOrMoreAreTrueByLoop(booleans, expectedTrueBooleans);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test(dataProvider = "test_cases_and_expected")
    public void should_check_two_true_booleans_by_operators(boolean[] booleans, boolean expected) {
        // given
        // when
        boolean result = ThreeBooleans
                .twoOrMoreAreTrueByOperators(booleans[0], booleans[1], booleans[2]);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test(dataProvider = "test_cases_and_expected")
    public void should_check_two_true_booleans_by_karnaugh_map(boolean[] booleans, boolean expected) {
        // given
        // when
        boolean result = ThreeBooleans
                .twoOrMoreAreTrueByKarnaughMap(booleans[0], booleans[1], booleans[2]);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test(dataProvider = "test_cases_and_expected")
    public void should_check_two_true_booleans_by_xor(boolean[] booleans, boolean expected) {
        // given
        // when
        boolean result = ThreeBooleans
                .twoOrMoreAreTrueByXor(booleans[0], booleans[1], booleans[2]);
        // then
        assertThat(result).isEqualTo(expected);
    }
}
