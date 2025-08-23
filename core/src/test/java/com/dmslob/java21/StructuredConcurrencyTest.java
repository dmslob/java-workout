package com.dmslob.java21;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class StructuredConcurrencyTest {
    final ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * Because the subtasks execute concurrently, each subtask can succeed or fail independently.
     * (Failure, in this context, means to throw an exception.)
     * Often, a task such as handle() should fail if any of its subtasks fail.
     * Understanding the lifetimes of the threads can be surprisingly complicated when failure occurs:
     * - If findUser() throws an exception then handle() will throw an exception when calling user.get()
     * but fetchOrder() will continue to run in its own thread.
     * This is a thread leak which, at best, wastes resources; at worst,
     * the fetchOrder() thread will interfere with other tasks.
     * - If the thread executing handle() is interrupted, the interruption will not propagate to the subtasks.
     * Both the findUser() and fetchOrder() threads will leak, continuing to run even after handle() has failed.
     * - If findUser() takes a long time to execute, but fetchOrder() fails in the meantime,
     * then handle() will wait unnecessarily for findUser() by blocking on user.get() rather than cancelling it.
     * Only after findUser() completes and user.get() returns will order.get() throw an exception,
     * causing handle() to fail.
     * In each case, the problem is that our program is logically structured with task-subtask relationships, but these relationships exist only in the developer's mind.
     * <p>
     * This not only creates more room for error, but it makes diagnosing and troubleshooting such errors more difficult.
     * Observability tools such as thread dumps, for example, will show handle(), findUser(), and fetchOrder()
     * on the call stacks of unrelated threads, with no hint of the task-subtask relationship
     */

    static class TooSlowException extends Exception {
        public TooSlowException(String s) {
            super(s);
        }
    }

    public Integer randomTask(int maxDuration, int threshold) throws InterruptedException, TooSlowException {
        int t = new Random().nextInt(maxDuration);
        if (t > threshold) {
            throw new TooSlowException(STR."Duration \{t} greater than threshold \{threshold}");
        }
        System.out.println(STR."Sub task result: \{t}");
        Thread.sleep(t);
        return t;
    }

    void handleShutdownOnFailure() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            List<StructuredTaskScope.Subtask<Integer>> subtasks = IntStream.range(0, 5)
                    .mapToObj(i -> scope.fork(
                            () -> randomTask(1000, 850))
                    )
                    .toList();
            scope.join()
                    .throwIfFailed();
            var totalDuration = subtasks.stream()
                    .map(StructuredTaskScope.Subtask::get)
                    .reduce(0, Integer::sum);
            System.out.println(STR."Total duration: \{totalDuration}");
        }
    }

    void handleShutdownOnSuccess() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess()) {
            IntStream.range(0, 5)
                    .mapToObj(i -> scope.fork(() -> randomTask(1000, 850)))
                    .toList();
            scope.join();
            System.out.println(STR."First task to finish: \{scope.result()}");
        }
    }

    @Test
    public void should_test_unstructured() throws ExecutionException, InterruptedException {
        var totalDuration = 0;
        List<Future<Integer>> subtasks = IntStream.range(0, 5)
                .mapToObj(i -> executor.submit(() -> randomTask(1000, 850))
                )
                .toList();
        for (Future<Integer> subtask : subtasks) {
            Integer i;
            try {
                i = subtask.get();
                totalDuration = totalDuration + i;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(STR."Total duration: \{totalDuration}");
    }

    @Test
    public void should_test_handleShutdownOnFailure() {
        try {
            System.out.println("Running handleShutdownOnFailure...");
            handleShutdownOnFailure();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void should_test_handleShutdownOnSuccess() {
        try {
            System.out.println("Running handleShutdownOnSuccess...");
            handleShutdownOnSuccess();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
