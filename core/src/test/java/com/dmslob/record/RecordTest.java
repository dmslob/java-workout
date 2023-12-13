package com.dmslob.record;

import org.testng.annotations.Test;

public class RecordTest {

    @Test
    public void should_test_record() {
        // given
        var mars = new Planet("Mars", 1000_000L);
        // when
        var name = mars.name();
        var size = mars.size();
        // then

    }

    record Planet(String name, long size) implements SpaceObject {
        public String name() {
            return name + size;
        }

        @Override
        public long size() {
            return size;
        }
    }

    interface SpaceObject {
        long size();
    }
}
