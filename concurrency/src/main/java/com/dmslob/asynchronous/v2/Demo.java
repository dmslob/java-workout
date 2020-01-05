package com.dmslob.asynchronous.v2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.dmslob.asynchronous.Util.delay;

/**
 * Created by Dmytro_Slobodenyuk on 8/20/2018.
 */

public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //completableFuture();
        //completableFutureWithCallback();
        //completableFutureWithCallbacks();
        //completableFutureWithThenCompose();
        //completableFutureWithTthenCombine();
        //completableFutureWithHandle();
        //completableFutureWithExceptionally();
        //cancelExample();
        //thenCombineExample();
        //thenComposeExample();
        //anyOfExample();
        //allOfExample();
        allOfAsyncExample();
    }

    public static void completableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            delay(500);
            return "Test";
        });
        System.out.println(future.get());
    }

    public static void completableFutureWithCallback() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            delay(500);
            return "Test with Callback";
        });

        future.thenAccept(result -> System.out.println(result));
        future.get();
    }

    public static void completableFutureWithCallbacks() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("supplyAsync(): " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().isDaemon());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            return "Test with Callback";
        });

        // Returns a new CompletionStage
        future.thenApply(result -> {
            System.out.println("thenApply(): " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
            System.out.println(result + " #1");
            return result;
        });

        future.thenApplyAsync(result -> {
            System.out.println("thenApplyAsync(): " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
            System.out.println(result + " #2");
            return result;
        });

        future.get();
    }

    public static void completableFutureWithThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10)
                .thenCompose(result ->
                        CompletableFuture.supplyAsync(() -> {
                            System.out.println(result);
                            return result * 2;
                        })).thenCompose(result ->
                        CompletableFuture.supplyAsync(() -> {
                            System.out.println(result);
                            return result * 5;
                        })
                );
        System.out.println(future.get());
    }

    public static void completableFutureWithTthenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> anotherFuture = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> result = future.thenCombine(anotherFuture, (a, b) -> a * b);
        System.out.println(result.get());
    }

    public static void completableFutureWithHandle() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> {
                    throw new RuntimeException("error in async running");
                }).handle((obj, err) -> {
                    if (obj != null) {
                        System.out.println(obj);
                    } else {
                        System.out.print(err.getMessage());
                    }
                    return 10;
                });

        System.out.println(" " + future.get());
    }

    public static void completableFutureWithExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("error in async running");
        }).exceptionally(err -> {
            return err.getMessage();
        }).thenAccept(System.out::println);
    }

    public static void cancelExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> 300);
        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");

        System.out.println("canceled: " + cf.cancel(true));
        System.out.println(cf.isCompletedExceptionally());
        System.out.println(cf2.join());
    }

    static void thenCombineExample() {
        String original = "original";
        String another = "another";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(another).thenApply(s -> delayedUpperCase(s)),
                        (s1, s2) -> s1 + ", " + s2);

        System.out.println(cf.getNow(null));
    }

    private static String delayedUpperCase(String s) {
        delay(1000);
        return s.toUpperCase();
    }

    public static void thenComposeExample() {
        String original = "original";
        String another = "another";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCompose(upper -> CompletableFuture.completedFuture(another).thenApply(s -> delayedUpperCase(s))
                        .thenApply(s -> upper + ", " + s));
        System.out.println(cf.getNow(null));
    }

    public static void anyOfExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if (th == null) {
                result.append(res);
            }
        });

        System.out.println(result.length() > 0);
    }

    public static void allOfExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> System.out.println(cf.getNow(null)));
            result.append("done");
        });

        System.out.println(result.length() > 0);
    }

    public static void allOfAsyncExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> System.out.println(cf.getNow(null)));
                    result.append("done");
                });

        allOf.join();
        System.out.println(result.length() > 0);
    }
}
