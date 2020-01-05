package com.dmslob.condition;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface Handoff<T> {

    void put(@Nonnull T t) throws InterruptedException;

    @Nonnull
    T take() throws InterruptedException;
}
