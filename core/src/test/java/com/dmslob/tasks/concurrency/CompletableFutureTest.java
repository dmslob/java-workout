package com.dmslob.tasks.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CompletableFutureTest {

    private static final Executor threadPool = Executors.newSingleThreadExecutor();

    String sendOrderPaidNotification() {
        log.info("Sending order paid notification");
        return "Test";
    }

    void saveOrderPaidEvent() {
        log.info("Saving order paid event");
        throw new RuntimeException("Error while saving order paid event");
    }

    CompletableFuture<Void> notifyOrderPaid() {
        return CompletableFuture
                .supplyAsync(this::sendOrderPaidNotification, threadPool)
                .thenAcceptAsync(result -> {
                    log.info("Order paid notification sent, result: {}", result);
                    saveOrderPaidEvent();
                }, threadPool);
    }

    @Test
    public void should_fail_when_supplyAsync() {
        // when
        var completableFuture = notifyOrderPaid();
        // then
        assertThat(completableFuture)
                .failsWithin(Duration.ofSeconds(1L))
                .withThrowableOfType(ExecutionException.class)
                .withCauseExactlyInstanceOf(RuntimeException.class)
                .withMessageContaining("Error while saving order paid event");
    }
}
