package com.dmslob.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class BlockingThreadPoolExecutor extends ThreadPoolExecutor {

    private static final Logger LOGGER = LogManager.getLogger(BlockingThreadPoolExecutor.class);

    private final Semaphore semaphore;

    public BlockingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue) {

        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        semaphore = new Semaphore(corePoolSize + 50);
    }

    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
    }

    @Override
    public void execute(final Runnable task) {
        boolean acquired = false;
        do {
            try {
                semaphore.acquire();
                acquired = true;
            } catch (final InterruptedException e) {
                LOGGER.info("InterruptedException whilst aquiring semaphore", e);
            }
        } while (!acquired);

        try {
            super.execute(task);
        } catch (final RejectedExecutionException e) {
            LOGGER.info("Task Rejected");
            semaphore.release();
            throw e;
        }
    }

    @Override
    protected void afterExecute(Runnable runnable, Throwable throwable) {
        super.afterExecute(runnable, throwable);
        if (throwable != null) {
            throwable.printStackTrace();
        }
        semaphore.release();
    }
}
