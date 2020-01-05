package com.dmslob.diningphilosophers;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface Chopstick {

    void acquireOwnership() throws InterruptedException;

    void releaseOwnership();
}
