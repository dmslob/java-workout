package com.dmslob.generics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class RawTypesTest {

	@Test
	public void should_throw_ClassCastException_when_raw_types_in_use() {
		// given
		String expectedMessage = "class java.lang.Integer cannot be cast to class java.lang.String";
		// A raw collection type - don't do this!
		List objects = new ArrayList();
		objects.add("user");
		objects.add(35);
		// when | then
		// Compile error - we need to cast
		//String expectedString = objects.get(0);
		String user = (String) objects.get(0);

		assertThat(user).isEqualTo("user");
		assertThatThrownBy(() -> {
			String expectedString = (String) objects.get(1);
		})
				.isInstanceOf(ClassCastException.class)
				.hasMessageContaining(expectedMessage);
	}

	@Test
	public void should_throw_ClassCastException_when_lose_type_safety() {
		// given
		String expectedMessage = "class java.lang.Integer cannot be cast to class java.lang.String";
		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, 42);

		// when | then
		assertThatThrownBy(() -> {
			String expectedString = strings.get(0); // Compiler-generated cast
		})
				.isInstanceOf(ClassCastException.class)
				.hasMessageContaining(expectedMessage);
	}

	void unsafeAdd(List list, Object o) {
		list.add(o);
	}

	@Test
	public void should_add_diff_type_item_when_use_raw_type() {
		// given
		ArrayList<String> strings = new ArrayList<>();
		strings.add("line");
		// when
		ArrayList arrayList = strings; // Ok
		strings = arrayList; // Unchecked assignment

		// then
		arrayList.add(1); //unchecked call

		assertThat(arrayList.get(0)).hasToString("line");
		assertThat(arrayList.get(1)).isEqualTo(1);
	}
}
