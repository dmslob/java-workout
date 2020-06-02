package com.dmslob;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DatabindTest {

    private static final String BIRTH_DATE_DEFAULT_VALUE = "1990-01-15";

    @Test
    public void objectToMapTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        User bobby = new User("Bobby", 35, "1");

        Map<String, Object> userPropertyToValue = objectMapper.convertValue(bobby, Map.class);
        System.out.println(userPropertyToValue);

        Map<String, String> resultMap = new HashMap<>();
        userPropertyToValue.entrySet().stream().forEach(entry -> {
            String[] keyArray = entry.getKey().split("(?=\\p{Upper})");
            String columnKey = Arrays.stream(keyArray).map(s -> s.toUpperCase())
                    .collect(Collectors.joining("_"));
            resultMap.put(columnKey, Objects.toString(entry.getValue()));
        });

        System.out.println(resultMap);
    }
}

class User {
    private String name;
    private int age;
    private String creditCardsCreationReqId;

    public User(String name, int age, String creditCardsCreationReqId) {
        this.name = name;
        this.age = age;
        this.creditCardsCreationReqId = creditCardsCreationReqId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreditCardsCreationReqId() {
        return creditCardsCreationReqId;
    }

    public void setCreditCardsCreationReqId(String creditCardsCreationReqId) {
        this.creditCardsCreationReqId = creditCardsCreationReqId;
    }
}

enum UserField {
    NAME("name"),
    AGE("age");

    public final String label;

    UserField(String label) {
        this.label = label;
    }
}
