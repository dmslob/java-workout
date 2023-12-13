package com.dmslob.future;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

public class CompletableFutureTest {

    private static final Logger LOGGER = LogManager.getLogger(CompletableFutureTest.class);

    @Test
    public void completeTaskTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Future's Result");
        String result = completableFuture.get();
        LOGGER.info("{}", result);

        assertEquals("Future's Result", result);
    }

    @Test
    public void runAsyncTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            sleep(1);
            LOGGER.info("I'll run in a separate thread than the main thread.");
        });
        while (!future.isDone()) {
            LOGGER.info("Do something else");
        }
        Void aVoid = future.get();

        assertNull(aVoid);
    }

    @Test
    public void asyncThreadIsDaemonTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            sleep(1);
            LOGGER.info("I'll run in a separate thread than the main thread.");
        });
        Void aVoid = future.get();
    }

    @Test
    public void supplyAsyncTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("{}", Thread.currentThread().toString());
            sleep(1);
            return "Result of the asynchronous computation";
        });
        while (!completableFuture.isDone()) {
            LOGGER.info("Do something else");
        }
        String futureResult = completableFuture.get();

        assertEquals("Result of the asynchronous computation", futureResult);
    }

    @Test
    public void completedFutureTest() {
        CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("message");

        assertTrue(completedFuture.isDone());
        assertEquals("message", completedFuture.getNow(null));
    }

    @Test
    public void thenApplyTest() throws ExecutionException, InterruptedException {
        var completedFuture =
                CompletableFuture.completedFuture("message")
                        .thenApply(s -> {
                            assertFalse(Thread.currentThread().isDaemon());
                            return s.toUpperCase();
                        });
        assertEquals("MESSAGE", completedFuture.getNow(null));
    }

    @Test
    public void should_supplyAsync_and_thenApply() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return null;
        }).thenApply(u -> {
            System.out.println(Thread.currentThread().getName());
            return null;
        });
    }

    @Test
    public void thenApplyAsyncWithExecutorTest() {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            LOGGER.info("{}", Thread.currentThread().toString());

            assertFalse(Thread.currentThread().isDaemon());

            sleep(1);

            return s.toUpperCase();
        }, executorService);

        assertNull(completedFuture.getNow(null));
        assertEquals("MESSAGE", completedFuture.join());
    }

    // Consuming the Result of the Previous Stage
    @Test
    public void thenAcceptTest() throws ExecutionException, InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        CompletableFuture<Void> completableFuture = CompletableFuture
                .completedFuture("Hello")
                .thenAccept(s -> {
                    LOGGER.info("{}", Thread.currentThread().toString());
                    LOGGER.info("Result of the Previous Stage is {}", s);

                    String newWord = "World";
                    stringBuilder.append(s).append(" ").append(newWord);
                });
        LOGGER.info("{}", stringBuilder.toString());
        assertTrue("Result was empty", stringBuilder.length() > 0);
    }

    // Asynchronously Consuming the Result of the Previous Stage
    @Test
    public void thenAcceptAsyncExample() {
        StringBuilder stringBuilder = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture
                .completedFuture("Hello")
                .thenAcceptAsync(s -> {
                    LOGGER.info("{}", Thread.currentThread().toString());
                    LOGGER.info("Result of the Previous Stage is {}", s);

                    String newWord = "World";
                    stringBuilder.append(s).append(" ").append(newWord);
                });
        cf.join();
        LOGGER.info("{}", stringBuilder.toString());

        assertTrue("Result was empty", stringBuilder.length() > 0);
    }

    @Test
    public void combineIndependentAsyncTasks() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cfOne = CompletableFuture.supplyAsync(() -> {
            sleep(2);
            LOGGER.info("cf_one...");
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            sleep(2);
            LOGGER.info("cf_two...");
            return "World";
        }), (s, s2) -> {
            sleep(2);
            LOGGER.info("combined");
            return s + " " + s2;
        });
        String futureResult = cfOne.get();
        LOGGER.info("{}", futureResult);

        assertEquals("Hello World", futureResult);
    }

    @Test
    public void applyToEitherExample() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> {
                    sleep(2000);
                    return s;
                });

        CompletableFuture<String> cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original)
                        .thenApplyAsync(s -> {
                            sleep(2000);
                            return s;
                        }), s -> s + " from applyToEither");
        assertTrue(cf2.join().endsWith(" from applyToEither"));
    }

    @Test
    public void isCompletedExceptionallyTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    return 1 / 0;
                })
                .exceptionally(exception -> {
                    LOGGER.error("{}", exception.getMessage());
                    return 1;
                });

        Integer futureResult = completableFuture.get();
        LOGGER.info("{}", futureResult);

        assertTrue(futureResult == 1);
    }

    private void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
