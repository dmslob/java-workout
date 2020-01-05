package com.dmslob.condition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AqsHandoff<T> implements Handoff<T> {

    private final ToggleSync emptySync;
    private final ToggleSync nonEmptySync;
    @Nullable
    private T t;

    public AqsHandoff() {
        this.emptySync = new ToggleSync();
        this.nonEmptySync = new ToggleSync();

        // nonEmptySync should block
        this.nonEmptySync.acquire(0);
    }

    @Override
    public void put(@Nonnull T t) throws InterruptedException {

        this.emptySync.acquire(0);
        try {
            this.t = t;
        } finally {
            this.nonEmptySync.release(0);
        }
    }

    @Nonnull
    @Override
    public T take() throws InterruptedException {
        this.nonEmptySync.acquire(0);
        try {
            T obj = this.t;
            this.t = null;
            return obj;
        } finally {
            this.emptySync.release(0);
        }
    }

    /**
     * Synchronizer state: 0 = nonempty, 1 = empty
     */
    private final static class ToggleSync extends AbstractQueuedSynchronizer {

        private static final int INACTIVE = 0;
        private static final int ACTIVE = 1;

        @Override
        protected boolean tryAcquire(int arg) {
            if (this.getState() == INACTIVE) {
                if (this.compareAndSetState(INACTIVE, ACTIVE)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (this.getState() == ACTIVE) {
                if (this.compareAndSetState(ACTIVE, INACTIVE)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean isHeldExclusively() {
            return this.getState() == ACTIVE;
        }
    }
}
