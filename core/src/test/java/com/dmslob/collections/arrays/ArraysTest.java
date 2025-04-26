package com.dmslob.collections.arrays;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArraysTest {

    @Test
    public void should_copy_references_when_item_is_array() {
        //given
        final String[][] src = {
                {"1", "2", "3", "4", "5"},
                {"6", "7", "8", "9", "10"}
        };
        //when
        String[][] dest = Arrays.copyOf(src, 3);
        src[1][1] = "4";
        //then
        assertThat(src[1]).isEqualTo(dest[1]);
    }

    @Test
    public void should_get_ArrayStoreException_when_test_covariance() {
        //given
        Integer[] integers = {1, 2, 3};
        Number[] numbers = integers; // valid
        //when
        Number firstElement = numbers[0]; // valid
        boolean isInteger = (firstElement instanceof Integer);
        //then
        assertThat(isInteger).isTrue();
        //when
        assertThatThrownBy(() -> numbers[0] = 4L)
                .isInstanceOf(ArrayStoreException.class);
        assertThatThrownBy(() -> numbers[0] = 4.5f)
                .isInstanceOf(ArrayStoreException.class);
    }

    @Test
    public void should_concatenate_arrays() {
        // given
        Integer[] intsA = {1, 2, 3};
        Integer[] intsB = {3, 4, 5};
        Integer[] expected = {1, 2, 3, 3, 4, 5};
        // when
        Integer[] actualResult = ArraysUtil.concat(intsA, intsB);
        // then
        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void should_concatenate_n_arrays() {
        // given
        Integer[] intsA = {1, 2, 3};
        Integer[] intsB = {3, 4, 5};
        Integer[] intsC = {6, 7, 8};
        Integer[] expected = {1, 2, 3, 3, 4, 5, 6, 7, 8};
        // when
        Integer[] actualResult = ArraysUtil.concat(intsA, intsB, intsC);
        // then
        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void should_reverse_array() {
        // given
        int[] ints = {1, 2, 3, 4, 5};
        int[] expected = {5, 4, 3, 2, 1};
        // when
        int[] actualResult = ArraysUtil.reverse(ints);
        // then
        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    public void should_check_if_array_is_sorted() {
        // given
        String[] lines = {"abc", "bcd", "cde"};
        // when
        boolean isSorted = ArraysUtil.isSorted(lines);
        // then
        assertThat(isSorted).isTrue();
        // given
        String[] names = {"Andrew", "Mary", "Bob", "Garry", "John"};
        // when
        isSorted = ArraysUtil.isSorted(names);
        // then
        assertThat(isSorted).isFalse();
        // when
        Arrays.sort(names);
        isSorted = ArraysUtil.isSorted(names);
        // then
        assertThat(isSorted).isTrue();
    }

    @Test
    public void should_search_by_KMP() {
        // given
        var pattern = "AAABAAA";
        var text = "ASBNSAAAAAABAAAAABAAAAAGAHUHDJKDDKSHAAJF";
        //                  ^     ^
        //                  |     |
        var expectedIndexes = List.of(8, 14);
        // when
        List<Integer> foundIndexes = ArraysUtil.performKMPSearch(text, pattern);
        // then
        assertThat(foundIndexes).isEqualTo(expectedIndexes);
        System.out.println("Pattern found in the given text String at positions: "
                + foundIndexes.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

    @Test
    public void should_sort_array_by_bubbleSort() {
        // given
        int[] nums = {9, 8, 13, 5, 34};
        int[] expectedSortedNums = {5, 8, 9, 13, 34};
        // when
        int[] actualSortedNums = ArraysUtil.bubbleSort(nums);
        // then
        assertThat(actualSortedNums).isEqualTo(expectedSortedNums);
    }

    @Test
    public void should_sort_array_by_insertionSort() {
        // given
        int[] nums = {9, 8, 13, 5, 34};
        int[] expectedSortedNums = {5, 8, 9, 13, 34};
        // when
        int[] actualSortedNums = ArraysUtil.insertionSort(nums);
        // then
        assertThat(actualSortedNums).isEqualTo(expectedSortedNums);
    }

    @Test
    public void should_sort_array_by_selectionSort() {
        // given
        int[] nums = {9, 8, 13, 5, 34};
        int[] expectedSortedNums = {5, 8, 9, 13, 34};
        // when
        int[] actualSortedNums = ArraysUtil.selectionSort(nums);
        // then
        assertThat(actualSortedNums).isEqualTo(expectedSortedNums);
    }

    @Test
    public void should_sort_array_by_quickSort() {
        // given
        int[] nums = {9, 8, 13, 5, 34};
        int[] expectedSortedNums = {5, 8, 9, 13, 34};
        // when
        ArraysUtil.quickSort(nums, 0, nums.length - 1);
        // then
        assertThat(nums).isEqualTo(expectedSortedNums);
    }

    @Test
    public void should_search_value_by_binarySearch() {
        // given
        int[] nums = new int[]{21, 41, 31, 12, 623, 543, 731, 1898};
        int expectedIndex = 6;
        // when
        Arrays.sort(nums);
        int i = ArraysUtil.binarySearch(nums, 731);
        // then
        assertThat(i).isEqualTo(expectedIndex);
        // when
        i = ArraysUtil.recursiveBinarySearch(nums, 0, nums.length, 731);
        // then
        assertThat(i).isEqualTo(expectedIndex);
    }

    @Test
    public void should_search_value_by_exponentialSearch() {
        // given
        int[] nums = new int[]{21, 41, 31, 12, 623, 543, 731, 1898};
        int expectedIndex = 6;
        // when
        Arrays.sort(nums);
        int i = ArraysUtil.exponentialSearch(nums, 731);
        // then
        assertThat(i).isEqualTo(expectedIndex);
    }

    @Test
    public void should_search_value_by_interpolationSearch() {
        // given
        int[] nums = new int[]{21, 41, 31, 12, 623, 543, 731, 1898};
        int expectedIndex = 6;
        // when
        Arrays.sort(nums);
        int i = ArraysUtil.interpolationSearch(nums, 731);
        // then
        assertThat(i).isEqualTo(expectedIndex);
    }

    @Test
    public void should_search_value_by_jumpSearch() {
        // given
        int[] nums = new int[]{21, 41, 31, 12, 623, 543, 731, 1898};
        int expectedIndex = 6;
        // when
        Arrays.sort(nums);
        int i = ArraysUtil.jumpSearch(nums, 731);
        // then
        assertThat(i).isEqualTo(expectedIndex);
    }
}