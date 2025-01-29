package com.dmslob.collections.roundrobin;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundRobinListTest {

    @Test
    public void should_get_elem_from_list() {
        // given
        var rrList = new RoundRobinList<>(List.of("host1", "host2"));
        // when | then
        assertThat(rrList.get()).isEqualTo("host1");
        assertThat(rrList.get()).isEqualTo("host2");
        assertThat(rrList.get()).isEqualTo("host1");
    }

    private static class RoundRobinList<T> {
        private final List<T> objects;
        private final AtomicInteger position = new AtomicInteger(0);
        private final int size;

        public RoundRobinList(List<T> objects) {
            this.objects = objects;
            this.size = objects.size();
        }

        public T get() {
            return objects.get(getNextPosition());
        }

        private int getNextPosition() {
            for (; ; ) {
                int current = position.get();
                int next = current + 1;
                if (next >= size) {
                    next = 0;
                }
                if (position.compareAndSet(current, next))
                    return current;
            }
        }
    }
}
