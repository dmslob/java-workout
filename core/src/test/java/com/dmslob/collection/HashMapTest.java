package com.dmslob.collection;

import com.dmslob.collection.map.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    private static final Logger LOGGER = LogManager.getLogger(HashMapTest.class);

    private List<String> words;
    private Map<String, Integer> wordToCountMap = new HashMap<>();

    @Before
    public void setUp() {
        words = Arrays.asList("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
    }

    @Test
    public void putTest() {
        Operation opOne = new Operation("123", new BigDecimal("10"));
        LOGGER.info("op key hashcode {}", opOne.hashCode());

        Operation opTwo = new Operation("123", new BigDecimal("10"));
        LOGGER.info("op key hashcode {}", opTwo.hashCode());

        Map<Operation, Integer> operationIntegerMap = new HashMap<>();

        operationIntegerMap.put(opOne, 1);
        operationIntegerMap.put(opTwo, 1);

        LOGGER.info(operationIntegerMap.size());
    }

    @Test
    public void orderItemsTest() {
        Map<Integer, Integer> integerMap = new HashMap<>();
        integerMap.put(10, 10);
        integerMap.put(20, 20);
        integerMap.put(30, 30);

        integerMap.keySet().forEach(System.out::println);
    }

    @Test
    public void countWordsOldTest() {
        words.forEach(word -> {
            Integer oldValue = wordToCountMap.get(word);
            if (oldValue == null) {
                wordToCountMap.put(word, 1);
            } else {
                wordToCountMap.put(word, oldValue + 1);
            }
        });
        LOGGER.info("Result: {}", wordToCountMap.toString());

        Assert.assertTrue(wordToCountMap.get("Foo") == 3);
    }

    @Test
    public void countWordsWithoutIfTest() {
        words.forEach(word -> {
            wordToCountMap.putIfAbsent(word, 0);
            wordToCountMap.computeIfPresent(word, (w, prev) -> prev + 1);
        });
        LOGGER.info("Result: {}", wordToCountMap.toString());

        Assert.assertTrue(wordToCountMap.get("Foo") == 3);
    }

    @Test
    public void countWordsWithMergeTest() {
        words.forEach(word -> wordToCountMap.merge(word, 1, (oldValue, newValue) -> oldValue + newValue));

        LOGGER.info("Result: {}", wordToCountMap.toString());

        Assert.assertTrue(wordToCountMap.get("Foo") == 3);
    }

    @Test
    public void countOpsWithMergeTest() {
        List<Operation> operations = getOps();
        Map<String, BigDecimal> balances = new HashMap<>();

        operations.forEach(op ->
                balances.merge(op.getAcc(), op.getAmount(), BigDecimal::add)
        );
        LOGGER.info("Result: {}", balances.toString());

        BigDecimal expectedResult = new BigDecimal(-100);
        Assert.assertTrue(balances.get("456").equals(expectedResult));
    }

    @Test
    public void getOrDefaultTest() {
        Integer expected = 500;
        Map<String, Integer> stringToIntegerMap = new HashMap<>();
        stringToIntegerMap.put("a", 100);
        stringToIntegerMap.put("b", 200);
        stringToIntegerMap.put("c", 300);
        stringToIntegerMap.put("d", 400);

        Integer actual = stringToIntegerMap.getOrDefault("e", 500);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void replaceAllTest() {
        HashMap<String, Integer> nameToYearOfBirthMap = new HashMap<>();
        nameToYearOfBirthMap.put("Anna", 2000);
        nameToYearOfBirthMap.put("Bobby", 2001);
        nameToYearOfBirthMap.put("David", 2003);
        nameToYearOfBirthMap.put("Lisa", 2002);

        nameToYearOfBirthMap.replaceAll((name, yearOfBirth) -> 2020 - yearOfBirth);
        Integer actual = nameToYearOfBirthMap.get("Anna");

        Assert.assertEquals(new Integer(20), actual);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void failFastTest() {
        Map<String, Integer> nameToScore = new HashMap<>();
        nameToScore.put("Bobby", 10);
        nameToScore.put("Jim", 20);

        for (String key : nameToScore.keySet()) {
            System.out.println(key);
            nameToScore.put("Anna", 11);
        }
    }

    @Test
    public void failSafeTest() {
        Map<String, Integer> nameToScore = new ConcurrentHashMap<>();
        nameToScore.put("Bobby", 10);
        nameToScore.put("Jim", 20);

        for (String key : nameToScore.keySet()) {
            System.out.println(key);
            nameToScore.put("Anna", 11);
        }

        nameToScore.keySet().forEach(key -> {
            System.out.println(key);
            nameToScore.put("Anna", 11);
        });
    }

    private List<Operation> getOps() {
        List<Operation> operations = Arrays.asList(
                new Operation("123", new BigDecimal("10")),
                new Operation("456", new BigDecimal("1200")),
                new Operation("123", new BigDecimal("-4")),
                new Operation("123", new BigDecimal("8")),
                new Operation("456", new BigDecimal("800")),
                new Operation("456", new BigDecimal("-1500")),
                new Operation("123", new BigDecimal("2")),
                new Operation("123", new BigDecimal("-6.5")),
                new Operation("456", new BigDecimal("-600"))
        );
        return operations;
    }
}