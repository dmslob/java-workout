package com.dmslob.lazyassignment;

import static org.assertj.core.api.AssertionsForClassTypes.in;
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
		Person arnold = new Person("Arnold", 30);
		LazyVar<Person> lazyPerson = LazyVar.let(() -> arnold);

		// when
		Person result = lazyPerson.get();

		// then
		assertThat(result).isEqualTo(arnold);
	}

	private static class Person {
		private final String name;
		private final int age;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Person person = (Person) o;
			if (age != person.age) {
				return false;
			}
			return name.equals(person.name);
		}

		@Override
		public int hashCode() {
			int result = name.hashCode();
			result = 31 * result + age;
			return result;
		}
	}
}
