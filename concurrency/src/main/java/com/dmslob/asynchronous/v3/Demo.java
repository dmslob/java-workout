package com.dmslob.asynchronous.v3;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Dmytro_Slobodenyuk on 8/21/2018.
 */
public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo demo = new Demo();
        // demo.completableFutureGetResult();
        // demo.testAsync();
        // demo.calculateBMI();
        // demo.testJoin();
        // demo.testAnyOf();
        // demo.testExceptionally();
        demo.testHandle();
    }

    public void testHandle() throws ExecutionException, InterruptedException {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if (ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    public void testExceptionally() throws ExecutionException, InterruptedException {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    // The problem with CompletableFuture.anyOf() is that if you have CompletableFutures that return results of different types,
    // then you won’t know the type of your final CompletableFuture.
    public void testAnyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

        System.out.println(anyOfFuture.get());
    }

    public void testJoin() throws ExecutionException, InterruptedException {
        List<String> webPageLinks = Arrays.asList("https://www.callicoder.com", "https://jaxenter.com", "https://able.bio");// A list of web page links

        // Download contents of all the web pages asynchronously
        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(webPageLink -> downloadWebPage(webPageLink))
                .collect(Collectors.toList());

        // Create a combined Future using allOf()
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()])
        );

        // When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
            return pageContentFutures.stream()
                    .map(pageContentFuture -> pageContentFuture.join())
                    .collect(Collectors.toList());
        });

        System.out.println(allPageContentsFuture.get());

        // Count the number of web pages having the "CompletableFuture" keyword.
        CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
            return pageContents.stream()
                    .filter(pageContent -> pageContent.contains("web"))
                    .count();
        });

        System.out.println("Number of Web Pages having CompletableFuture keyword - " +
                countFuture.get());
    }

    public CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            // Code to download and return the web page's content
            return "Content of web page -> " + pageLink;
        });
    }

    public void calculateBMI() throws ExecutionException, InterruptedException {
        //LOGGER.info("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                //LOGGER.info(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        //LOGGER.info("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                //LOGGER.info(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 172.8;
        });

        //LOGGER.info("Calculating BMI (Body Mass Index)...");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm / 100;
                    return weightInKg / (heightInMeter * heightInMeter);
                });

        //LOGGER.info("Your BMI is - " + combinedFuture.get());
    }

    public void testAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<CompletableFuture<Double>> result = getUsersDetail(1)
                .thenApply(user -> getCreditRating(user));

        result.get();
    }

    public CompletableFuture<User> getUsersDetail(long userId) {
        return CompletableFuture.supplyAsync(() -> {
            //LOGGER.info("Call remote service to get User with ID = " + userId);
            return getUser(userId);
        });
    }

    public CompletableFuture<Double> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> {
            //LOGGER.info("Call remote service to get credit rating by user = " + user.getUserName());
            double userRating = getCreditRatingByUser(user);
            //LOGGER.info("User Rating: " + userRating);
            return userRating;
        });
    }

    // fake method
    private User getUser(long userId) {
        return new User(userId, "Dmytro Slobodenyuk");
    }

    // fake method
    private double getCreditRatingByUser(User user) {
        return 34.45;
    }

    public void completableFutureGetResult() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        }).thenApply(res -> {
            /*
              Executed in the same thread where the supplyAsync() task is executed
              or in the main thread If the supplyAsync() task completes immediately (Remove sleep() call to verify)
            */
            return "[ " + res;
        }).thenApplyAsync(res -> {
            // Executed in a different thread from ForkJoinPool.commonPool()
            return res + " ]";
        });

        future.thenAccept(res -> {
            System.out.println("Result " + res);
        });

        future.thenRun(() -> {
            System.out.println("No access to result");
        });

        future.get();
    }
}

class User {
    private long id;
    private String userName;

    public User(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}