package com.dmslob.collections;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class HashMapTest {

    @Test
    public void should_iterate_over_map_in_different_way() {
        // given
        HashMap<String, Integer> items = new HashMap<>();
        items.put("coins", 5);
        items.put("pens", 2);
        items.put("chairs", 7);
        // when iterate over
        // Stream api
        items.entrySet().stream().forEach(e ->
                System.out.format("key: %s, value: %d%n", e.getKey(), e.getValue()));
        // for each
        items.forEach((key, value) -> System.out.format("key: %s, value: %d%n", key, value));
        // enhanced for
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.format("key: %s, value: %d%n", entry.getKey(), entry.getValue());
        }
        // Iterator
        Iterator<Map.Entry<String, Integer>> it = items.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.format("key: %s, value: %d%n", entry.getKey(), entry.getValue());
        }
        // for iterator
        for (Iterator<Map.Entry<String, Integer>> i = items.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<String, Integer> entry = i.next();
            System.out.format("key: %s, value: %d%n", entry.getKey(), entry.getValue());
        }
        // key set iterator
        Iterator<String> i = items.keySet().iterator();
        while (i.hasNext()) {
            String key = i.next();
            System.out.format("key: %s, value: %d%n", key, items.get(key));
        }
        // then
        System.out.format("keys: %s%n", items.keySet());
        System.out.format("values: %s%n", items.values());
    }

    @Test
    public void should_filter_map_by_value() {
        // given
        Map<String, String> capitals = new HashMap<>();
        capitals.put("svk", "Bratislava");
        capitals.put("ger", "Berlin");
        capitals.put("hun", "Budapest");
        capitals.put("czk", "Prague");
        capitals.put("pol", "Warsaw");
        capitals.put("ita", "Rome");
        // when
        Map<String, String> filteredCapitals = capitals.entrySet().stream()
                .filter(entry -> entry.getValue().startsWith("B"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        // then
        assertThat(filteredCapitals.keySet()).isEqualTo(Set.of("svk", "ger", "hun"));
    }

    @Test
    public void should_verify_keySet_and_values_are_unmodifiable() {
        // given
        Map<String, Double> itemToPrice = new HashMap<>();
        itemToPrice.put("Rice", 6.9);
        itemToPrice.put("Egg", 1.9);
        Set<String> keys = itemToPrice.keySet();
        var values = itemToPrice.values();
        // when | then
        assertThatThrownBy(() -> keys.add("Tea"))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> values.add(34.9))
                .isInstanceOf(UnsupportedOperationException.class);
        // But when iterate over entrySet is OK
        Set<Map.Entry<String, Double>> entries = itemToPrice.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            if (entry.getKey().equals("Egg")) {
                entry.setValue(45.6); // OK
            }
        }
        // then
        assertThat(itemToPrice.get("Egg")).isEqualTo(45.6);
    }

    @Test
    public void should_calculate_value_even_if_key_is_present_when_call_putIfAbsent() {
        // given
        Map<String, Double> itemToPrice = new HashMap<>();
        itemToPrice.put("Rice", 6.9);
        itemToPrice.put("Egg", 1.9);
        var theKey = "Fish";
        // when
        itemToPrice.put(theKey, 34.6);
        //Even though if the key is present, the callExpensiveMethodToFindValue will get called
        itemToPrice.putIfAbsent(theKey, callExpensiveMethodToFindValue(theKey));
        // then
        assertThat(itemToPrice.get(theKey)).isEqualTo(34.6);
    }

    @Test
    public void should_call_method_even_if_key_is_present_when_call_getOrDefault() {
        // given
        Map<String, Double> itemToPrice = new HashMap<>();
        itemToPrice.put("Rice", 6.9);
        itemToPrice.put("Egg", 1.9);
        var theKey = "Fish";
        // when
        itemToPrice.put(theKey, 34.6);
        var val = itemToPrice.getOrDefault(theKey, callExpensiveMethodToFindValue(theKey));
        // then
        assertThat(val).isEqualTo(34.6);
    }

    @Test
    public void should_not_calculate_value_when_key_is_present() {
        // given
        Map<String, Double> itemToPrice = new HashMap<>();
        itemToPrice.put("Rice", 6.9);
        itemToPrice.put("Egg", 1.9);
        var theKey = "Fish";
        // when
        itemToPrice.put(theKey, 34.6);
        //The callExpensiveMethodToFindValue will never get called if key is already present
        itemToPrice.computeIfAbsent(theKey, this::callExpensiveMethodToFindValue);
        // then
        assertThat(itemToPrice.get(theKey)).isEqualTo(34.6);
    }

    Double callExpensiveMethodToFindValue(String val) {
        System.out.println(STR."invoked...\{val}");
        return 23.54;
    }

    @Test
    public void should_get_sub_map() {
        // given
        Map<String, String> capitals = new HashMap<>();
        capitals.put("svk", "Bratislava");
        capitals.put("ger", "Berlin");
        capitals.put("hun", "Budapest");
        capitals.put("czk", "Prague");
        capitals.put("pol", "Warsaw");
        capitals.put("ita", "Rome");
        // when | then
        var keys = List.of("hun", "pol");
        Map<String, String> subMap = capitals.entrySet().stream()
                //.skip(4)
                .filter(x -> keys.contains(x.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        // then
        assertThat(subMap.containsKey("hun")).isTrue();
        assertThat(subMap.containsKey("pol")).isTrue();
        // or
        subMap = new HashMap<>(capitals);
        subMap.keySet().retainAll(keys);
        // then
        assertThat(subMap.containsKey("hun")).isTrue();
        assertThat(subMap.containsKey("pol")).isTrue();
        assertThat(subMap.size()).isEqualTo(2);
    }

    String fetchAndReturnNew(String key, String oldValue) {
        return "newValue";
    }

    String fetchAndReturnNull(String key, String oldValue) {
        return null;
    }

    String fetchAndThrowError(String key, String oldValue) {
        throw new RuntimeException("error");
    }

    @Test
    public void should_return_new_value_when_computeIfPresent() {
        // given
        var expected = "newValue";
        Map<String, String> capitalCities = new HashMap<>();
        capitalCities.put("Norway", "empty");
        capitalCities.put("Germany", "Berlin");
        // when
        String norwayCapital = capitalCities
                .computeIfPresent("Norway", this::fetchAndReturnNew);
        // then
        assertThat(norwayCapital).isEqualTo(expected);
        assertThat(capitalCities.get("Norway")).isEqualTo(expected);
    }

    @Test
    public void should_remove_mapping_when_computeIfPresent() {
        // given
        Map<String, String> capitalCities = new HashMap<>();
        capitalCities.put("Norway", "empty");
        capitalCities.put("Germany", "Berlin");
        // when
        String norwayCapital = capitalCities
                .computeIfPresent("Norway", this::fetchAndReturnNull);
        // then
        assertThat(norwayCapital).isEqualTo(null);
        assertThat(capitalCities.containsKey("Norway")).isFalse();
    }

    @Test
    public void should_do_nothing_when_computeIfPresent() {
        // given
        var expected = "empty";
        Map<String, String> capitalCities = new HashMap<>();
        capitalCities.put("Norway", "empty");
        capitalCities.put("Germany", "Berlin");
        // when | then
        assertThatThrownBy(() -> capitalCities
                .computeIfPresent("Norway", this::fetchAndThrowError));
        assertThat(capitalCities.containsKey("Norway")).isTrue();
        assertThat(capitalCities.get("Norway")).isEqualTo(expected);
    }

    @Test
    public void should_merge_values_for_keys() {
        // given
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("apple", 10);
        inventory.put("banana", 5);
        inventory.put("orange", 8);
        // when
        inventory.computeIfPresent("banana", (key, value) -> value - 2);
        inventory.computeIfAbsent("grape", k -> 7);
        inventory.merge("apple", 5, Integer::sum);
        inventory.merge("orange", 3, (oldVal, newVal) -> oldVal * newVal);
        // then
        System.out.println(inventory);
    }
}
