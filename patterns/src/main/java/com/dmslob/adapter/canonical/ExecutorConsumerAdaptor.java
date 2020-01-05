package com.dmslob.adapter.canonical;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class ExecutorConsumerAdaptor implements Consumer<Runnable> {

    private final Executor executor;

    public ExecutorConsumerAdaptor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void accept(Runnable task) {
        executor.execute(task);
    }
}
