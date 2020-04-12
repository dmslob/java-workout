package com.dmslob.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.*;

public class ExecutorServiceTest {

    private static final Logger LOGGER = LogManager.getLogger(ExecutorServiceTest.class);

    @Test
    public void asyncOperationTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<Double> doubleCallable = () -> doSomeLongComputation();
        Future<Double> doubleFuture = executorService.submit(doubleCallable);
        doSomethingElse();

        try {
            Double aDouble = doubleFuture.get(1, TimeUnit.SECONDS);
            LOGGER.info("{}", aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void doSomethingElse() {
        LOGGER.info("doSomethingElse()");
        sleep(2);
    }

    private Double doSomeLongComputation() {
        LOGGER.info("doSomeLongComputation()");
        sleep(5);
        return new Double(12.0);
    }

    private void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
