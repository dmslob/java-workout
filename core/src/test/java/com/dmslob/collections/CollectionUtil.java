package com.dmslob.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtil {
	/**
	 * Union of two lists
	 *
	 * @param first  list
	 * @param second list
	 * @return the union of two list
	 */
	public static <T> List<T> union(List<T> first, List<T> second) {
		Set<T> setOfItems = new HashSet<>();
		setOfItems.addAll(first);
		setOfItems.addAll(second);
		return new ArrayList<>(setOfItems);
	}

	/**
	 * Find intersection
	 *
	 * @param first  list
	 * @param second list
	 * @return items in common
	 */
	public static <T> List<T> intersection(List<T> first, List<T> second) {
		return first.stream()
				.filter(second::contains)
				.distinct()
				.collect(Collectors.toList());
	}

	public static <T> List<T> merge(List<T> first, List<T> second) {
		return Stream.of(first, second)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	public static <T> List<T> mergeWithoutDuplicatesBySet(List<T> first, List<T> second) {
		Set<T> set = new LinkedHashSet<>(first);
		set.addAll(second);
		return new ArrayList<>(set);
	}

	public static <T> List<T> mergeWithoutDuplicates(List<T> first, List<T> second) {
		second.removeAll(first);
		first.addAll(second);
		return first;
	}
}