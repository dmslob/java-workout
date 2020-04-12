package com.dmslob.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.*;

public class CustomThreadPoolExecutorTest {

    private static final Logger LOGGER = LogManager.getLogger(CustomThreadPoolExecutorTest.class);
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int KEEP_ALIVE_TIME = 5000;

    @Test
    public void rejectedTaskTest() {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);
        CustomThreadPoolExecutor poolExecutor =
                new CustomThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, blockingQueue);
        run(poolExecutor);
    }

    @Test
    public void throttlingTaskTest() {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(50);
        BlockingThreadPoolExecutor poolExecutor =
                new BlockingThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, blockingQueue);
        run(poolExecutor);
    }

    private void run(ThreadPoolExecutor poolExecutor) {
        poolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
                LOGGER.info("Task Rejected : " + ((WorkerTask) runnable).getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info("Lets add another time : " + ((WorkerTask) runnable).getName());
                executor.execute(runnable);
            }
        });
        // Let start all core threads initially
        poolExecutor.prestartAllCoreThreads();

        Integer threadCounter = 0;
        while (true) {
            threadCounter++;
            // Adding threads one by one
            poolExecutor.execute(new WorkerTask(threadCounter.toString()));
            if (threadCounter == 100) {
                break;
            }
        }
    }
}
