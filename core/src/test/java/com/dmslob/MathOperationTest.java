package com.dmslob;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class MathOperationTest {

	@Test
	public void should_throw_arithmetic_exception_when_divide_by_zero() {
		// given
		int a = 12;
		int b = 0;

		// when | then
		assertThatThrownBy(() -> log.info("{}", a / b))
				.isInstanceOf(ArithmeticException.class);
	}

	@Test
	public void should_not_throw_arithmetic_exception_when_divide_by_zero() {
		// given
		int zero = 0;
		float f = 12.2f;
		double d = 8098098.8790D;

		// when
		float resultFloat = f / zero;
		double resultDouble = d / zero;

		// then
		assertThat(resultFloat).isEqualTo(Float.POSITIVE_INFINITY);
		assertThat(resultDouble).isEqualTo(Double.POSITIVE_INFINITY);
	}

	@Test
	public void should_not_be_equals_when_plus_operation_with_two_doubles() {
		// given
		double expected = 0.3;
		// when
		double result = 0.1 + 0.2; //0.30000000000000004
		// then
		assertThat(result).isNotEqualTo(expected);
	}

	@Test
	public void should_not_be_equals_when_two_big_decimals() {
		// given
		BigDecimal bd1 = new BigDecimal("0.2");
		log.info("{}", bd1); // 0.2

		BigDecimal bd2 = new BigDecimal(0.2);
		// 0.200000000000000011102230246251565404236316680908203125
		log.info("{}", bd2);

		// when
		boolean equals = bd1.equals(bd2);
		int compare = bd1.compareTo(bd2);

		// then
		assertThat(equals).isFalse();
		assertThat(compare).isEqualTo(-1);
	}

	@Test
	public void should_assign_hex() {
		// given
		int expectedResult = 255;
		int hexNumber = 0xff;
		// when | then
		assertThat(hexNumber).isEqualTo(expectedResult);
	}

	@Test
	public void should_cast_hex_to_byte() {
		// given
		byte expectedResult = -1;
		int hexNumber = 0xff;

		// when
		/* Since Java represents a byte using 8 bits
		 * and because a byte is a signed data type,
		 * the value of 0xff is -1
		 */
		byte actualResult = (byte) hexNumber;
		// then
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	public void should_test_bitwise_left_shift() {
		// given
		int expectedResult = 8;
		// when
		int actualResult = 4 << 1; // 4 * (2 ^ 1)
		// then
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	public void should_test_bitwise_right_shift() {
		// given
		int expectedResult = 2;
		// when
		int actualResult = 4 >> 1; // 4 / (2 ^ 1)
		// then
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	public void should_find_log2() {
		// given
		int n = 1024;
		int expected = 10;
		// when
		int actualResult = log2(n);
		log.info("Log {} to the base 2 = {}", n, actualResult);
		// then
		Assert.assertEquals(expected, actualResult);
	}

	private static int log2(int n) {
		return ((int) (Math.log(n) / Math.log(2)));
	}
}
