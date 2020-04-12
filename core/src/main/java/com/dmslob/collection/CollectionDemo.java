package com.dmslob.collection;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionDemo {

    public static void main(String[] args) {
        CollectionDemo collectionDemo = new CollectionDemo();

        List<String> listOne = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "f"));
        List<String> listTwo = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        listOne.add("");
        List<String> mergedList = collectionDemo.merge(listOne, listTwo);
        System.out.println("Merge: " + mergedList);

        collectionDemo.compareLists();

        List<String> first = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> second = new ArrayList<>(Arrays.asList("B", "C", "D", "E", "F"));

        List<String> unions = collectionDemo.union(first, second);
        System.out.println("Union: " + unions.toString());

        List<String> intersection = collectionDemo.intersection(first, second);
        System.out.println(intersection.toString());

        List<String> strings = collectionDemo.intersectionWithStream(first, second);
        System.out.println(strings.toString());

        second.retainAll(first);
        System.out.println(second.toString());

        first.retainAll(second);
        System.out.println(first.toString());

        List<String> theList = new ArrayList<>(Arrays.asList("red", "blue", "green", "yellow"));
        List<String> otherList = new ArrayList<>(Arrays.asList("red", "white", "black", "yellow"));
        theList.retainAll(otherList);
        System.out.println(theList);
    }

    void compareLists() {
        ArrayList<Integer> listIntegers = new ArrayList<>();
        listIntegers.add(1);
        listIntegers.add(2);
        listIntegers.add(3);

        ArrayList<Integer> listOtherIntegers = new ArrayList<>();
        listOtherIntegers.add(1);
        listOtherIntegers.add(2);
        listOtherIntegers.add(3);

        boolean eq = listIntegers.equals(listOtherIntegers);
        System.out.println(eq);
    }

    void failFastIteration() {
        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);

        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 3) {
                iterator.remove(); // ok!
            }
        }
        System.out.println(values.toString());

        iterator = values.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 1) {
                values.remove(1); // ConcurrentModificationException
                values.add(1); // ConcurrentModificationException
            }
        }
        howMany(true, new boolean[]{true, true});
    }

    int howMany(boolean b, boolean... b2) {
        System.out.println(b2.length);
        return b2.length;
    }

    void reverseLinkedList() {
        List<String> strings = new LinkedList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        Collections.reverse(strings);
        System.out.println(strings);
        // or
        List<String> reversedList = new LinkedList<>();
        LinkedList<String> stringLinkedList = (LinkedList<String>) strings;
        while (!stringLinkedList.isEmpty()) {
            reversedList.add(stringLinkedList.poll());
        }
        System.out.println(reversedList);

    }

    void unmodifiableList() {
        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);

        List<Integer> immutableValues = Collections.unmodifiableList(values);

        values.add(4);
        immutableValues.set(0, 4); // java.lang.UnsupportedOperationException
        //immutableValues.add(7); // java.lang.UnsupportedOperationException

        System.out.println(values.toString());
        System.out.println(immutableValues.toString());

        Integer[] arr = {1, 3, 4};
        List<Integer> ints = (List<Integer>) Arrays.asList(arr);
        // ints.add(5); //java.lang.UnsupportedOperationException
        System.out.println(ints);
    }

    <T> List<T> union(List<T> first, List<T> second) {
        Set<T> setOfItems = new HashSet<>();
        setOfItems.addAll(first);
        setOfItems.addAll(second);
        return new ArrayList<>(setOfItems);
    }

    <T> List<T> intersection(List<T> first, List<T> second) {
        List<T> resultList = new ArrayList<>();
        for (T t : first) {
            if (second.contains(t)) {
                resultList.add(t);
            }
        }
        return resultList;
    }

    <T> List<T> intersectionWithStream(List<T> first, List<T> second) {
        Set<T> result = first.stream()
                .distinct()
                .filter(second::contains)
                .collect(Collectors.toSet());

        return new ArrayList<T>(result);
    }

    public <T> List<T> merge(List<T> firstList, List<T> secondList) {
        firstList.addAll(secondList);
        return firstList;
    }

    public <T> List<T> mergeByStream(List<T> firstList, List<T> secondList) {
        List<T> combinedList = Stream.of(firstList, secondList)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        return combinedList;
    }

    public <T> List<T> mergeListsWithoutDuplicatesBySet(List<T> firstList, List<T> secondList) {
        Set<T> set = new LinkedHashSet<>(firstList);
        set.addAll(secondList);
        List<T> combinedList = new ArrayList<>(set);
        return combinedList;
    }

    public <T> List<T> mergeListsWithoutDuplicates(List<T> firstList, List<T> secondList) {
        List<T> listTwoCopy = new ArrayList<>(secondList);
        listTwoCopy.removeAll(firstList);
        firstList.addAll(listTwoCopy);
        return firstList;
    }

    void removeElements() {
        List<String> strings = new ArrayList<>();
        strings.add("Magician");
        strings.add("Assistant");
        System.out.println(strings); // [Magician, Assistant]

        strings.removeIf(s -> s.startsWith("A"));
        System.out.println(strings); // [Magician]
    }
}