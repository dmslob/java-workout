package com.dmslob.types;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BasicTypeTest {

    @Test
    public void should_test_binary_value_as_int() {
        // given
        int binary = 0b10;
        int expected = 2;
        // when | then
        assertThat(binary).isEqualTo(expected);
    }

    @Test
    public void should_test_octal_value_as_int() {
        // given
        int octal = 017;
        int expected = 15;
        // when | then
        assertThat(octal).isEqualTo(expected);
    }

    @Test
    public void should_test_hexadecimal_value_as_int() {
        // given
        int hexadecimal = 0xFF;
        int expected = 255;
        // when | then
        assertThat(hexadecimal).isEqualTo(expected);
    }

    @Test
    public void should_calculate_pow() {
        // given
        double expected = 8.0;
        // when
        double actualResult = pow(2, 3);
        // then
        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void should_count_bits_for_number() {
        // given
        int expected = 3;
        // when
        int actualResult = countBits(100);
        // then
        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void should_reverse_number() {
        // given
        long expected = 7654;
        // when
        long reversed = reverse(4567);
        // then
        assertThat(reversed).isEqualTo(expected);
    }

    long reverse(int x) {
        long result = 0;
        long xRemaining = Math.abs(x);
        while (xRemaining != 0) {
            result = result * 10 + xRemaining % 10;
            xRemaining /= 10;
        }
        return x < 0 ? -result : result;
    }

    short countBits(int x) {
        short numBits = 0;
        while (x != 0) {
            numBits += (x & 1);
            x >>>= 1;
        }
        return numBits;
    }

    double pow(double x, int y) {
        double result = 1.0;
        long power = y;
        if (y < 0) {
            power = -power;
            x = 1.0 / x;
        }
        while (power != 0) {
            if ((power & 1) != 0) {
                result *= x;
            }
            x *= x;
            power >>>= 1;
        }
        return result;
    }
}