package com.dmslob.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.LongFunction;
import java.util.stream.LongStream;

import org.testng.annotations.Test;

public class StreamBenchmarks {
	private static final long[] STREAM_SIZES = {1_000L, 10_000L, 100_000L, 1_000_000L};

	public static long seqSumRangeClosed(long n) {
		return LongStream.rangeClosed(1L, n).sum();
	}

	public static long parallelSumRangeClosed(long n) {
		return LongStream.rangeClosed(1L, n).parallel().sum();
	}

	public static long seqSumIterate(long n) {
		return LongStream.iterate(1L, i -> i + 1).limit(n).sum();
	}

	public static long parallelSumIterate(long n) {
		return LongStream.iterate(1L, i -> i + 1).limit(n).parallel().sum();
	}

	public static long iterSumLoop(long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		return result;
	}

	/*
	 * Applies the function parameter func, passing n as parameter.
	 * Returns the average time (ms.) to execute the function 100 times.
	 */
	public static <R> double measurePerf(LongFunction<R> func, long n) {
		int numOfExecutions = 100;
		double totalTime = 0.0;
		R result = null;
		for (int i = 0; i < numOfExecutions; i++) {
			double start = System.nanoTime();
			result = func.apply(n);
			double duration = (System.nanoTime() - start) / 1_000_000;
			totalTime += duration;
		}
		return (totalTime / numOfExecutions);
	}

	/*
	 * Executes list of functions for different stream sizes.
	 */
	public static <R> void xqtFunctions(List<LongFunction<R>> functions) {
		// For each stream size ...
		for (long streamSize : STREAM_SIZES) {
			System.out.printf("%7d", streamSize);
			// execute the functions.
			for (LongFunction<R> function : functions) {
				System.out.printf("%10.5f", measurePerf(function, streamSize));
			}
			System.out.println();
		}
	}

	@Test
	public void should_benchmark_streams() {
		System.out.println("Streams created with the rangeClosed() method:");
		System.out.println("Size \tSequential \tParallel");
		xqtFunctions(Arrays.asList(StreamBenchmarks::seqSumRangeClosed, StreamBenchmarks::parallelSumRangeClosed));

		System.out.println("Streams created with the iterate() method:");
		System.out.println("Size \tSequential \tParallel");
		xqtFunctions(Arrays.asList(StreamBenchmarks::seqSumIterate, StreamBenchmarks::parallelSumIterate));

		System.out.println("Iterative solution with an explicit loop:");
		System.out.println("Size Iterative");
		xqtFunctions(Collections.singletonList(StreamBenchmarks::iterSumLoop));
	}
}
