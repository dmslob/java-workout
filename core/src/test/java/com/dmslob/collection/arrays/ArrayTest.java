package com.dmslob.collection.arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArrayTest {

	@Test
	public void should_generate_and_init_array_by_stream() {
		// given | when
		Record[] records = Stream
				.generate(() -> new Record(1))
				.limit(32)
				.toArray(Record[]::new);
		// then
		assertThat(records)
				.isNotEmpty()
				.hasSize(32);
		assertThat(records[0].val).isOne();
	}

	private static class Record {
		int val;

		public Record(int val) {
			this.val = val;
		}
	}

	@Test
	public void should_init_arrays() {
		//c-style array declaration
		int ints[] = new int[10];

		int[] ints_1 = new int[10];
		int[] ints_2 = {0, 0};

		// compile error
		//int ints[10] = new int[10];
		//int[] ints = new int[0]{};
	}

	@Test
	public void should_copy_references_when_item_is_array() {
		//given
		final String[][] src = {
				{"A"},
				{"B", "C"},
				{"D", "E", "F"}
		};
		//when
		String[][] dest = Arrays.copyOf(src, 3);
		src[2][2] = "X";
		//then
		assertThat(src[2]).isEqualTo(dest[2]);
	}

	@Test
	public void should_have_the_same_size_when_item_is_null() {
		//given
		final String[] cars = new String[] {"BMW", "Ford", "Lexus"};
		//when
		cars[0] = null;
		//then
		assertEquals(3, cars.length);
		assertThat(cars[0]).isNull();
	}

	@Test(expected = ArrayStoreException.class)
	public void covarianceTest() {
		//given
		Integer[] integers = {1, 2, 3};
		Number[] numbers = integers; // valid
		//when
		Number firstElement = numbers[0]; // valid
		//then
		assertThat((firstElement instanceof Integer)).isTrue();
		//when
		numbers[0] = 4L;
		//then
		assertThat((numbers[0] instanceof Long)).isTrue();
		//when
		numbers[0] = 4.5F;
		//then
		assertThat((numbers[0] instanceof Float)).isTrue();
	}

	@Test
	public void should_compare_arrays() {
		// given
		int[][] a = getA();
		int[][] b = getB();
		int[][] c = getC();

		int[] aa = new int[] {1, 2, 3};
		int[] bb = new int[] {1, 2, 3};

		//then
		assertThat(a).isEqualTo(b);
		assertThat(a).isEqualTo(c);

		boolean isAAEqualBB = aa.equals(bb); // same as (aa == bb)
		assertThat(isAAEqualBB).isFalse(); //"false" because a and b refer to different objects

		isAAEqualBB = Arrays.equals(aa, bb);
		assertThat(isAAEqualBB).isTrue(); //"true" because the elements of a and b are equals
	}

	private int[][] getA() {
		return new int[0][];
	}

	private int[] getB()[] {
		return new int[][] {};
	}

	private int getC()[][] {
		return new int[0][];
	}

	@Test
	public void invarianceTest() {
		//given
		List<Integer> integers = Arrays.asList(1, 2, 3);
		// List<Number> numbers = integers; // compile error

		//when and then
		assertThatThrownBy(() -> integers.add(4))
				.isInstanceOf(UnsupportedOperationException.class);

		List<? extends Number> numbers = integers;
		Number firstElement = numbers.get(0);

		Integer expectedValue = 1;
		assert firstElement.equals(expectedValue);
		//numbers.set(0, 4L); // compile error
	}

	@Test
	public void should_find_only_one_duplicate() {
		// given
		List<Integer> expected = List.of(3, 6);
		List<Integer> numbers = new ArrayList<>(List.of(10, 2, 3, 3, 6, 7, 6));
		// when
		var duplicates = findDuplicates(numbers);
		// then
		assertThat(duplicates).isEqualTo(expected);
	}

	private List<Integer> findDuplicates(List<Integer> numbers) {
		Collections.sort(numbers);
		var duplicates = new ArrayList<Integer>();
		for (int i = 0; i < numbers.size() - 1; i++) {
			if ((numbers.get(i) ^ numbers.get(i + 1)) == 0) {
				duplicates.add(numbers.get(i));
			}
		}
		return duplicates;
	}
}