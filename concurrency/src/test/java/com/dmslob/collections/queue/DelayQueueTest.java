package com.dmslob.collections.queue;

import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    @Test
    public void givenDelayQueue() throws InterruptedException {
        // given
        var DQ = new DelayQueue<DelayItem>();
        DQ.add(new DelayItem("A", 1));
        DQ.add(new DelayItem("B", 2));
        DQ.add(new DelayItem("C", 3));
        DQ.add(new DelayItem("D", 4));

        System.out.println("DelayQueue: " + DQ);

        var DQ2 = new DelayQueue<DelayItem>(DQ);

        System.out.println("DelayQueue: " + DQ2);
        System.out.println("DelayQueue: " + DQ2.take());
    }
}

class DelayItem implements Delayed {
    private final String name;
    private final long time;

    public DelayItem(String name, long delayTime) {
        this.name = name;
        this.time = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = time - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed obj) {
        return Long.compare(this.time, ((DelayItem) obj).time);
    }

    @Override
    public String toString() {
        return "\n{"
                + "name=" + name
                + ", time=" + time
                + "}";
    }
}
