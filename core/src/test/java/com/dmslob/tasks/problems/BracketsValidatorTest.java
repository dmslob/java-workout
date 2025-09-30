package com.dmslob.tasks.problems;

import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BracketsValidatorTest {

	@Test
	public void should_return_empty_list_when_expression_is_valid() {
		// given
		BracketsValidator validator = new BracketsValidator();
		String expression = "(1+2)/(1+7)";

		// when
		List<Integer> noValidPositions =
				validator.validate(expression);

		// then
		assertThat(noValidPositions).isEmpty();
	}

	@Test
	public void should_return_no_valid_positions_when_expression_is_not_valid() {
		// given
		List<Integer> expectedPositions = List.of(5, 3);
		BracketsValidator validator = new BracketsValidator();
		String expression = "1+2)/(1+7";

		// when
		List<Integer> noValidPositions =
				validator.validate(expression);

		// then
		assertThat(noValidPositions).isEqualTo(expectedPositions);
	}
}
