package com.dmslob.collections;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CollectionsTest {

    private final List<String> topics = new ArrayList<>();
    private final List<String> newTopics = new ArrayList<>();

    @BeforeMethod
    public void setUp() {
        topics.add("practice");
        topics.add("code");
        topics.add("quiz");
        topics.add("geeks for geeks");

        newTopics.add("geeks");
        newTopics.add("geek");
        newTopics.add("for");
        newTopics.add("coder");
    }

    @Test
    public void invariance_test() {
        // given
        List<Integer> integers = Arrays.asList(1, 2, 3);
        // List<Number> numbers = integers; // compile error
        // when
        List<? extends Number> numbers = integers;
        Number firstElement = numbers.getFirst();
        // then
        Integer expectedValue = 1;
        assert firstElement.equals(expectedValue);
        //numbers.set(0, 4L); // compile error
    }

    Set<String> findAndSortTagsByFrequency(List<String> tweets) {
        final Map<String, Long> tweetToCountMap = tweets.stream()
                .flatMap(lineWithTweets -> Arrays.stream(lineWithTweets.split(" ")))
                .filter(tweet -> tweet.matches("#\\w+"))
                //.filter(tweet -> tweet.startsWith("#"))
                .collect(Collectors.groupingBy(tweet -> tweet, Collectors.counting()));

        final Map<String, Long> sortedByCount = tweetToCountMap.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Long> comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return sortedByCount.keySet();
    }

    @Test
    public void should_collect_tags_by_frequency() {
        // given
        List<String> tweets = new ArrayList<>();
        tweets.add("#Java and #Scala and #Scala and #Scala");
        tweets.add("#Java and #Kotlin and #Groovy");
        tweets.add("#Java and #Scala and #Kotlin");
        // when
        String actualResult = findAndSortTagsByFrequency(tweets).toString();
        // then
        Assert.assertEquals("[#Scala, #Java, #Kotlin, #Groovy]", actualResult);
    }

    @Test
    public void should_join_collections() {
        // when
        topics.addAll(newTopics);
        // then
        Assert.assertEquals(8, topics.size());
    }

    @Test
    public void should_define_if_collections_are_disjoint() {
        // when
        boolean isDisjoint = Collections.disjoint(topics, newTopics);
        // then
        Assert.assertTrue(isDisjoint);
    }

    @Test
    public void should_sum_numbers_by_reduce_method() {
        // given
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        // when
        Integer sum = integers.stream()
                .reduce(0, Integer::sum);
        // then
        assertThat(sum).isEqualTo(15);
    }

    @Test
    public void should_sum_numbers_by_collect_method() {
        // given
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        // when
        Integer sum = integers.stream().mapToInt(Integer::intValue).sum();
        // then
        assertThat(sum).isEqualTo(15);
    }

    @Test
    public void should_reverse_list() {
        // given
        List<String> expectedList = new LinkedList<>(List.of("3", "2", "1"));
        List<String> strings = new LinkedList<>(List.of("1", "2", "3"));

        // when
        var reversedList = reverseList(strings);

        // then
        assertThat(reversedList).isEqualTo(expectedList);
    }

    private static <T> List<T> reverseList(List<T> list) {
        List<T> reversedList = new LinkedList<>();
        LinkedList<T> linkedList = (LinkedList<T>) list;
        while (!linkedList.isEmpty()) {
            reversedList.add(linkedList.removeLast());
        }
        return reversedList;
    }

    @Test
    public void should_test_fail_fast_iteration() {
        // given
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3));

        // when
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 3) {
                iterator.remove(); // OK!
            }
        }
        // then
        assertThat(integers).isEqualTo(List.of(1, 2));

        // but when
        iterator = integers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 1) {
                integers.remove(1); // ConcurrentModificationException
                integers.add(1); // ConcurrentModificationException
            }
        }
    }

    @Test
    public void should_union_lists() {
        // given
        var first = List.of("a", "b", "c", "d", "f");
        var second = List.of("a", "b", "c", "d", "e");
        var expected = List.of("a", "b", "c", "d", "e", "f");
        // when
        var union = CollectionUtil.union(first, second);
        // then
        assertThat(union).isEqualTo(expected);
    }

    @Test
    public void should_find_intersection() {
        // given
        var expected = List.of("B", "C");
        var first = List.of("A", "B", "C");
        var second = List.of("B", "C", "D", "E", "F");
        // when
        var intersected = CollectionUtil.intersection(first, second);
        // then
        assertThat(intersected).isEqualTo(expected);
    }

    @Test
    public void should_find_intersection_by_retainAll() {
        // given
        var expected = List.of("B", "C");
        var first = List.of("A", "B", "C");
        var second = new ArrayList<>(List.of("B", "C", "D", "E", "F"));
        // when
        second.retainAll(first);
        // then
        assertThat(second).isEqualTo(expected);
    }

    @Test
    public void should_merge_lists_with_duplicates() {
        // given
        var expected = List.of("A", "B", "C", "B", "C", "D", "E", "F");
        var first = List.of("A", "B", "C");
        var second = List.of("B", "C", "D", "E", "F");
        // when
        var merged = CollectionUtil.merge(first, second);
        // then
        assertThat(merged).isEqualTo(expected);
    }

    @Test
    public void should_merge_lists_without_duplicates_by_set() {
        // given
        var expected = List.of("A", "B", "C", "D", "E", "F");
        var first = List.of("A", "B", "C");
        var second = List.of("B", "C", "D", "E", "F");
        // when
        var merged = CollectionUtil.mergeWithoutDuplicatesBySet(first, second);
        // then
        assertThat(merged).isEqualTo(expected);
    }

    @Test
    public void should_merge_lists_without_duplicates() {
        // given
        var expected = List.of("A", "B", "C", "D", "E", "F");
        var first = new ArrayList<>(List.of("A", "B", "C"));
        var second = new ArrayList<>(List.of("B", "C", "D", "E", "F"));
        // when
        var merged = CollectionUtil.mergeWithoutDuplicates(first, second);
        // then
        assertThat(merged).isEqualTo(expected);
    }

    @Test
    public void should_test_raw_types() {
        // given
        var strings = new ArrayList<String>();
        strings.add("ID12");
        // when
        List objects = strings;
        objects.add(2);
        // then
        assertThat(objects).contains("ID12");
        assertThat(objects).contains(2);

        // when
        var typeSafeList = Collections.checkedList(objects, String.class);

        // then
        assertThatThrownBy(() -> typeSafeList.add(2))
                .isInstanceOf(ClassCastException.class);
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