package com.dmslob.data.problems;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumNumbersTest {

    @Test
    public void should_sum_numbers() {
        // given
        var in = 153;
        var expectedSum = 9;

        // when
        var sum = sumNumbers(in);

        // then
        assertThat(sum).isEqualTo(expectedSum);
    }

    private int sumNumbers(int in) {
        int res = 0;
        while (in > 0) {
            res += in % 10;
            in = in / 10;
        }
        return res;
    }
}
