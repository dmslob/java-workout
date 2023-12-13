package com.dmslob.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NavigableSetTest {

	@Test
	public void should_reverse_set_of_integers() {
		// given
		NavigableSet<Integer> integers = givenSetOfIntegers();

		// when
		NavigableSet<Integer> reversedSet = integers.descendingSet();

		// then
		log.info("Normal order: {}", integers);
		log.info("Reversed order: {}", reversedSet);

		assertThat(reversedSet).isNotEqualTo(integers);
	}

	@Test
	public void should_tail_set_of_integers() {
		// given
		NavigableSet<Integer> integers = givenSetOfIntegers();
		// when
		NavigableSet<Integer> threeOrMore = integers.tailSet(3, true);
		// then
		log.info("3 or  more:  " + threeOrMore);
		log.info("lower(3): {}", integers.lower(3));
		log.info("floor(3): {}", integers.floor(3));
		log.info("higher(3): {}", integers.higher(3));
		log.info("ceiling(3): {}", integers.ceiling(3));
	}

	@Test
	public void should_poll_items() {
		// given
		NavigableSet<Integer> integers = givenSetOfIntegers();
		// when and then
		log.info("Navigable Set: {}", integers);
		log.info("pollFirst(): {}", integers.pollFirst());
		log.info("pollLast(): {}", integers.pollLast());
		log.info("pollFirst(): {}", integers.pollFirst());
		log.info("pollFirst(): {}", integers.pollFirst());
		log.info("pollFirst(): {}", integers.pollFirst());
		log.info("pollFirst(): {}", integers.pollFirst());
		log.info("pollLast(): {}", integers.pollLast());
	}

	@Test
	public void should_get_head_of_set() {
		// given
		NavigableSet<Integer> integers = givenSetOfIntegers();
		// when
		final NavigableSet<Integer> headSet = integers.headSet(3, true);
		// then
		log.info("HeadSet: {}", headSet);
	}

	private NavigableSet<Integer> givenSetOfIntegers() {
		return new TreeSet<>(Arrays.asList(2, 4, 1, 3, 5, 0, 6, 8, 7, 9));
	}
}
