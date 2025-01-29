package com.dmslob.collections;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SetTest {

    @Test
    public void addAllTest() {
        Set<String> commonPoolNames = new HashSet<>();
        commonPoolNames.add("Anna");
        commonPoolNames.add("Teddy");

        Set<String> newNames = new HashSet<>();
        newNames.add("Anna");
        newNames.add("Billy");

        boolean modified = commonPoolNames.addAll(newNames);
        log.info("{}", commonPoolNames);

        Assert.assertTrue(modified);
    }
}
