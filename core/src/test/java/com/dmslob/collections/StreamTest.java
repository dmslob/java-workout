package com.dmslob.collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.testng.annotations.Test;

public class StreamTest {

	@Test
	public void should_return_pairs_whose_sum_is_divisible_by_3() {
		// given
		List<Integer> numbersOne = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> numbersTwo = Arrays.asList(6, 7, 8, 9, 10);

		// when
		List<int[]> pairs = numbersOne.stream()
				.flatMap(i -> numbersTwo.stream()
						.filter(j -> (i + j) % 3 == 0)
						.map(j -> new int[] {i, j}))
				.collect(Collectors.toList());

		// then
		assertNotNull(pairs);
		assertThat(pairs).hasSize(8);
		assertThat(pairs.get(0)).contains(1, 8);
		assertThat(pairs.get(1)).contains(2, 7);
		assertThat(pairs.get(2)).contains(2, 10);
		assertThat(pairs.get(3)).contains(3, 6);
		assertThat(pairs.get(4)).contains(3, 9);
		assertThat(pairs.get(5)).contains(4, 8);
		assertThat(pairs.get(6)).contains(5, 7);
		assertThat(pairs.get(7)).contains(5, 10);
	}

	@Test
	public void already_closed_stream_when_use_flatMap() {
		// given
		var javaTagsStream = Stream.of("Java", "Nodejs", "JavaScript")
				.filter(s -> s.contains("Java"));
		var otherTags = List.of("Java", "Python", "Scala");

		// when
		var intersectedTags = otherTags.stream()
				.flatMap(s -> javaTagsStream.filter(s::equals))
				.toList();
		// java.lang.IllegalStateException: stream has already been operated upon or closed
		javaTagsStream
				.filter(s -> s.contains("Node"))
				.collect(Collectors.toList());
	}

	@Test
	public void should_flatten_collection() {
		// given
		List<List<String>> nestedList = Arrays.asList(
				Collections.singletonList("one:one"),
				Arrays.asList("two:one", "two:two", "two:three"),
				Arrays.asList("three:one", "three:two", "three:three", "three:four"));

		// when
		List<String> strings = flattenListOfLists(nestedList);

		// then
		assertNotNull(strings);
		assertThat(strings).hasSize(8);
	}

	@Test
	public void should_find_pythagorean_triangle_numbers() {
		// given | when
		Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
						.mapToObj(b -> new int[] {a, b, (int) Math.sqrt(a * a + b * b)}));
		// then
		pythagoreanTriples.limit(5)
				.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
		// or
		// given | when
		Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.mapToObj(b -> new double[] {a, b, Math.sqrt(a * a + b * b)})
						.filter(t -> t[2] % 1 == 0));
		// then
		pythagoreanTriples2.limit(5)
				.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
	}

	private <T> List<T> flattenListOfLists(List<List<T>> list) {
		return list.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}
}
