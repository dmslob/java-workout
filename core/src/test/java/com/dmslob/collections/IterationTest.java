package com.dmslob.collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IterationTest {

    @Test
    public void should_set_item() {
        // given
        var letters = new ArrayList<>(List.of("A", "B", "C", "D"));
        Consumer<String> setElement = letter -> {
            if (letter != null && letter.equals("D")) {
                letters.set(3, "G");
            }
        };
        // when
        letters.forEach(setElement);
        // then
        assertThat(letters).contains("A", "B", "C", "G");
    }

    @Test
    public void should_throw_exception_when_add_or_remove_item() {
        // given
        var letters = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Consumer<String> addElement = letter -> {
            if (letter != null && letter.equals("A")) {
                letters.add("D");
            }
        };
        Consumer<String> removeElement = letter -> {
            if (letter != null && letter.equals("A")) {
                letters.remove("D");
            }
        };
        // when | then
        assertThatThrownBy(() -> letters.forEach(addElement))
                .isInstanceOf(ConcurrentModificationException.class);
        assertThatThrownBy(() -> letters.forEach(removeElement))
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void should_not_get_ConcurrentModificationException_when_modify_list_by_iterator() {
        // given
        var strings = new ArrayList<>(List.of("1", "2"));
        Iterator<String> it = strings.iterator();
        // when
        while (it.hasNext()) {
            String value = it.next();
            if (value.equals("2")) {
                //strings.remove(value); // Any modification of collection causes ConcurrentModificationException
                //strings.add("55"); // ConcurrentModificationException
                it.remove(); // OK
            }
        }
        // then
        assertThat(strings).isEqualTo(List.of("1"));
    }

    @Test
    public void should_get_UnsupportedOperationException_when_modify_list_by_iterator() {
        // given
        var strings = new CopyOnWriteArrayList<>(List.of("1", "2"));
        Iterator<String> iterator = strings.listIterator();
        // when | then
        assertThatThrownBy(() -> {
            while (iterator.hasNext()) {
                String val = iterator.next();
                if ("2".equals(val)) {
                    iterator.remove();
                }
            }
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void should_not_get_ConcurrentModificationException() {
        // given
        var strings = new CopyOnWriteArrayList<>(List.of("1", "2", "3", "5"));
        // when
        for (String s : strings) {
            if ("2".equals(s)) {
                strings.remove(s);
            }
        }
        Iterator<String> listIterator = strings.iterator();
        while (listIterator.hasNext()) {
            String value = listIterator.next();
            if (value.equals("3")) {
                strings.remove("4");
                strings.add("6");
                strings.add("7");
            }
        }
        assertThat(strings).isEqualTo(List.of("1", "3", "5", "6", "7"));

        // given
        Map<String, String> numberMap = new ConcurrentHashMap<>();
        numberMap.put("1", "1");
        numberMap.put("2", "2");
        numberMap.put("3", "3");
        Iterator<String> mapIterator = numberMap.keySet().iterator();
        // when
        while (mapIterator.hasNext()) {
            String key = mapIterator.next();
            if (key.equals("1")) {
                numberMap.remove("3");
                numberMap.put("4", "4");
                numberMap.put("5", "5");
            }
        }
        // then
        assertThat(numberMap)
                .isEqualTo(Map.of("1", "1", "2", "2", "4", "4", "5", "5"));
    }
}
