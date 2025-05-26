package com.dmslob.collections.roundrobin;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundRobinListTest {

    @Test
    public void should_get_elem_from_list() {
        // given
        var rrList = new RoundRobinList<>(List.of("host1", "host2", "host3", "host4", "host5", "host6"));
        // when | then
        assertThat(rrList.get()).isEqualTo("host1");
        assertThat(rrList.get()).isEqualTo("host2");
        assertThat(rrList.get()).isEqualTo("host3");
        assertThat(rrList.get()).isEqualTo("host4");
        assertThat(rrList.get()).isEqualTo("host5");
        assertThat(rrList.get()).isEqualTo("host6");
        assertThat(rrList.get()).isEqualTo("host1");
    }

    static class RoundRobinList<T> {
        private final List<T> objects;
        private final AtomicInteger currentIndex = new AtomicInteger(0);

        public RoundRobinList(List<T> objects) {
            this.objects = objects;
        }

        public T get() {
            return objects.get(next());
        }

        private int next() {
            return Math.abs(currentIndex.getAndIncrement() % objects.size());
        }
    }

    static class StickySessionBalancer<T> {
        private final List<T> objects;
        private final Map<String, T> clientSessionMap = new HashMap<>();
        private int nextIndex = 0;

        public StickySessionBalancer(List<T> objects) {
            this.objects = objects;
        }

        public T get(String sessionId) {
            T object = clientSessionMap.get(sessionId);
            if (object == null) {
                T o = objects.get(nextIndex);
                nextIndex = Math.abs((nextIndex + 1) % objects.size());
                clientSessionMap.put(sessionId, o);
            }
            return clientSessionMap.get(sessionId);
        }
    }
}
