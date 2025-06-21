package com.dmslob.java21;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SequencedCollectionTest {

    @Test
    public void should_test_ArrayList_as_SequencedCollection() {
        // given
        // ArrayList now implements SequencedCollection
        SequencedCollection<String> names = new ArrayList<>();
        // when
        names.addLast("Alice"); // Equivalent to add("Alice") for ArrayList
        names.addLast("Bob");
        names.addFirst("Charlie"); // Adds to the beginning
        var expectedList = List.of("Charlie", "Alice", "Bob");
        // then
        assertThat(names).isEqualTo(expectedList);
        assertThat(names.getFirst()).isEqualTo("Charlie");
        assertThat(names.getLast()).isEqualTo("Bob");

        assertThat(names.removeFirst()).isEqualTo("Charlie");
        assertThat(names.removeLast()).isEqualTo("Bob");
        assertThat(names).isEqualTo(List.of("Alice"));
        // Add more elements for reversed view
        names.addLast("David");
        names.addLast("Eve");

        // Collection before reverse
        assertThat(names).isEqualTo(List.of("Alice", "David", "Eve"));
        // Get a reversed view
        SequencedCollection<String> reversedNames = names.reversed();
        // Reversed view
        assertThat(reversedNames).isEqualTo(List.of("Eve", "David", "Alice"));
        // Modifications to the reversed view affect the original collection (if supported)
        reversedNames.removeFirst(); // Removes 'Eve' from the original collection
        // Original collection after reversed view modification
        assertThat(names).isEqualTo(List.of("Alice", "David"));
        // Reversed view after modification
        assertThat(reversedNames).isEqualTo(List.of("David", "Alice"));
    }

    @Test
    public void should_test_LinkedHashSet_as_SequencedSet() {
        // given
        // LinkedHashSet now implements SequencedSet
        SequencedSet<String> uniqueItems = new LinkedHashSet<>();
        // when
        uniqueItems.add("Apple");
        uniqueItems.add("Banana");
        uniqueItems.addFirst("Cherry"); // Adds to the beginning
        uniqueItems.addLast("Date");    // Adds to the end
        // then
        assertThat(uniqueItems).isEqualTo(Set.of("Cherry", "Apple", "Banana", "Date"));
        // Repositioning an existing element
        uniqueItems.addFirst("Banana"); // "Banana" moves to the front
        assertThat(uniqueItems).isEqualTo(Set.of("Banana", "Cherry", "Apple", "Date"));

        assertThat(uniqueItems.getFirst()).isEqualTo("Banana");
        assertThat(uniqueItems.getLast()).isEqualTo("Date");

        SequencedSet<String> reversedItems = uniqueItems.reversed();
        assertThat(reversedItems).isEqualTo(Set.of("Date", "Apple", "Cherry", "Banana"));
    }

    @Test
    public void should_test_LinkedHashMap_as_SequencedMap() {
        // given
        // LinkedHashMap now implements SequencedMap
        SequencedMap<Integer, String> studentScores = new LinkedHashMap<>();
        studentScores.put(101, "Alice");
        studentScores.put(103, "Bob");
        studentScores.putFirst(100, "Charlie"); // Puts at the beginning
        studentScores.putLast(104, "David");    // Puts at the end
        assertThat(studentScores).isEqualTo(Map.of(
                100, "Charlie",
                101, "Alice",
                103, "Bob",
                104, "David"));
        // Get first and last entries
        Map.Entry<Integer, String> firstEntry = studentScores.firstEntry();
        Map.Entry<Integer, String> lastEntry = studentScores.lastEntry();
        assertThat(firstEntry.getKey()).isEqualTo(100);
        assertThat(lastEntry.getKey()).isEqualTo(104);
        // Remove entries
        Map.Entry<Integer, String> removedFirstEntry = studentScores.pollFirstEntry();
        Map.Entry<Integer, String> removedLastEntry = studentScores.pollLastEntry();
        assertThat(removedFirstEntry.getKey()).isEqualTo(100);
        assertThat(removedLastEntry.getKey()).isEqualTo(104);
        assertThat(studentScores).isEqualTo(Map.of(
                101, "Alice",
                103, "Bob"));
        // Get reversed map view
        SequencedMap<Integer, String> reversedMap = studentScores.reversed();
        assertThat(studentScores).isEqualTo(Map.of(
                103, "Bob",
                101, "Alice"));
        // Access sequenced key set, values, and entry set
        assertThat(studentScores.sequencedKeySet())
                .isEqualTo(Set.of(101, 103));
        assertThat(studentScores.sequencedValues().stream().toList())
                .isEqualTo(List.of("Alice", "Bob"));
        assertThat(studentScores.sequencedEntrySet()
                .stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .isEqualTo(Map.of(101, "Alice", 103, "Bob"));
    }

    @Test
    public void should_throw_UnsupportedOperationException_when_invoke_sequenced_methods_on_sortedSet() {
        // given
        SortedSet<Integer> sortedSet = new TreeSet<>();
        // when
        sortedSet.add(1);
        sortedSet.add(2);
        // then
        assertThatThrownBy(() -> sortedSet.addFirst(3))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> sortedSet.addLast(3))
                .isInstanceOf(UnsupportedOperationException.class);
        // when
        assertThat(sortedSet.removeFirst()).isEqualTo(1);
        assertThat(sortedSet.removeLast()).isEqualTo(2);

        sortedSet.reversed();
    }
}
