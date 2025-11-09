package com.dmslob.tasks.problems;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MapNestedSearchTest {

    @DataProvider
    public static Object[][] user_DataProvider() {
        return new Object[][]{
                {null, "unknown"},
                {" ", "unknown"},
                {"data", "unknown"},
                {"data.user", "unknown"},
                {"user.address.city", "London"},
                {"user.age", "30"},
                {"user.address.country", "UK"},
        };
    }

    @Test(dataProvider = "user_DataProvider")
    public void should_handle_nested_map(String path, String expected) {
        // given
        String defaultValue = "unknown";
        Map<String, Object> data = Map.of(
                "user", Map.of(
                        "name", "John",
                        "age", "30",
                        "address", Map.of(
                                "city", "London",
                                "country", "UK"
                        )
                )
        );
        // when
        String actual = getNestedValue(data, path, defaultValue);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    String getNestedValue(Map<String, Object> map, String path, String defaultValue) {
        if (map == null || path == null || path.trim().isEmpty()) {
            return defaultValue;
        }
        String[] keys = path.split("\\.");
        if (keys.length == 0) return defaultValue;
        if (!map.containsKey(keys[0])) return defaultValue;
        Object current = map;
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            if (!(current instanceof Map)) return defaultValue;
            Map<String, Object> currentMap = (Map<String, Object>) current;
            Object value = currentMap.get(key);
            if (i == keys.length - 1) {
                try {
                    return (value == null ? defaultValue : (String) value);
                } catch (ClassCastException e) {
                    return defaultValue;
                }
            } else {
                if (value == null || !currentMap.containsKey(key)) {
                    return defaultValue;
                }
                current = value;
            }
        }
        return defaultValue;
    }
}
