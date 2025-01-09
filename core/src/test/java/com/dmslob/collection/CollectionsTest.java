package com.dmslob.collection;

import com.dmslob.collection.stream.tasks.TweeterTagsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CollectionsTest {

    private final List<String> topics = new ArrayList<>();
    private final List<String> newTopics = new ArrayList<>();

    @Before
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
    public void should_copy_list_ref() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        List<String> unmodifiable = Collections.unmodifiableList(strings);
        strings.add("3");

        System.out.println(strings);
        System.out.println(unmodifiable);
    }

    @Test
    public void should_find_item_by_binarySearch() {
        // when
        int index = Collections.binarySearch(topics, "quiz");
        // then
        assertThat(index).isPositive();
        assertThat(index).isEqualTo(2);
    }

    @Test
    public void should_collect_tags_by_frequency() {
        // given
        List<String> tweets = new ArrayList<>();
        tweets.add("#Java and #Scala and #Scala and #Scala");
        tweets.add("#Java and #Kotlin and #Groovy");
        tweets.add("#Java and #Scala and #Kotlin");

        TweeterTagsService tagsService = new TweeterTagsService();

        // when
        String actualResult = tagsService.findAndSortTagsByFrequency(tweets).toString();

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
    public void should_reverse_list_by_reverse_from_java() {
        // given
        List<String> expectedList = new LinkedList<>(List.of("3", "2", "1"));
        List<String> strings = new LinkedList<>(List.of("1", "2", "3"));

        // when
        Collections.reverse(strings);

        // then
        assertThat(strings).isEqualTo(expectedList);
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

    record User(Long id, List<String> interests) {}

    @Test
    public void should_find_users_by_interest() {
        // given
        List<User> users = List.of(
                new User(0L, List.of("Hadoop", "Big Data", "HBase", "Java", "Spark", "Cassandra")),
                new User(1L, List.of("NoSQL", "MongoDB", "Cassandra", "HBase", "Postgres")),
                new User(2L, List.of("Python", "scikit-learn", "scipy", "numpy", "statsmodels", "pandas")),
                new User(3L, List.of("R", "Python", "statistics", "regression", "probability")),
                new User(4L, List.of("machine learning", "regression", "decision trees", "regression", "libsvm")),
                new User(5L, List.of("Python", "R", "Java", "C++", "Haskell", "programming languages")),
                new User(6L, List.of("statistics", "probability", "mathematics", "theory")),
                new User(7L, List.of("machine learning", "scikit-learn", "Mahout", "neural networks"))
        );
        // when
        var usersByInterest = usersByInterest(users, "Python");
        // then
        System.out.println(usersByInterest);
    }

    private Map<String, List<Long>> usersByInterest(List<User> users, String interest) {
        if (Objects.isNull(users) || users.isEmpty()) {
            return Map.of();
        }
        List<Long> userIds = users.stream()
                .filter(user -> user.interests().contains(interest))
                .map(User::id)
                .toList();
        return Map.of(interest, userIds);
    }

    @Test
    public void should_check_collection() {
        // given
        var ids = new ArrayList<String>();
        ids.add("ID12");
        // when
        List objIds = ids;
        objIds.add(2);
        // then
        assertThat(objIds).contains("ID12");
        assertThat(objIds).contains(2);

        // when
        var typeSafeList = Collections.checkedList(objIds, String.class);

        // then
        assertThatThrownBy(() -> typeSafeList.add(2))
                .isInstanceOf(ClassCastException.class);
    }
}