package com.dmslob.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Dmytro_Slobodenyuk on 8/29/2018.
 */

public class BenchmarkExample {
    @State(Scope.Group)
    public static class GroupState {
        final AtomicLong atomicLong = new AtomicLong();
        final LongAdder longAdder = new LongAdder();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Group("fourThreadAtomic")
    @GroupThreads(4)
    @Warmup(iterations = 2)
    @Fork(value = 2)
    @Measurement(iterations = 5)
    public long fourThreadAtomic(GroupState groupState) {
        return groupState.atomicLong.incrementAndGet();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Group("fourThreadAdder")
    @GroupThreads(4)
    @Warmup(iterations = 2)
    @Fork(value = 2)
    @Measurement(iterations = 5)
    public long fourThreadAdder(GroupState groupState) {
        groupState.longAdder.increment();
        return groupState.longAdder.longValue();
    }
}
