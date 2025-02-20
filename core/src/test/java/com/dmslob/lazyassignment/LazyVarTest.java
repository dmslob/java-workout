package com.dmslob.lazyassignment;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.testng.annotations.Test;

public class LazyVarTest {

	@Test
	public void should_wrap_string_value() {
		// given
		String stringValue = "Test";
		LazyVar<String> lazyString = LazyVar.let(() -> stringValue);
		// when
		String result = lazyString.get();
		// then
		assertThat(result).isEqualTo(stringValue);
	}

	@Test
	public void should_wrap_int_value() {
		// given
		Integer intValue = 1;
		LazyVar<Integer> lazyInt = LazyVar.let(() -> intValue);
		// when
		Integer result = lazyInt.get();
		// then
		assertThat(result).isEqualTo(intValue);
	}

	@Test
	public void should_wrap_object() {
		// given
		record Person(String name, int age) {}
		var arnold = new Person("Arnold", 30);
		LazyVar<Person> lazyPerson = LazyVar.let(() -> arnold);
		// when
		Person result = lazyPerson.get();
		// then
		assertThat(result).isEqualTo(arnold);
	}
}
