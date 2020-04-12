package com.dmslob.collection;

import com.dmslob.collection.map.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapTest {

    private static final Logger LOGGER = LogManager.getLogger(HashMapTest.class);

    private List<String> words;
    private Map<String, Integer> wordToCountMap = new HashMap<>();

    @Before
    public void setUp() {
        words = Arrays.asList("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
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
