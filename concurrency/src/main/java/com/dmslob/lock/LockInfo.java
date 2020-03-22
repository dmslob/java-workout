package com.dmslob.lock;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class LockInfo {

    ExecutorService executor = Executors.newFixedThreadPool(2);
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInfo lockInfo = new LockInfo();

        lockInfo.executor.submit(() -> {
            lockInfo.lock.lock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockInfo.lock.unlock();
            }
        });

        lockInfo.executor.submit(() -> {
            System.out.println("Locked: " + lockInfo.lock.isLocked());
            System.out.println("Held by me: " + lockInfo.lock.isHeldByCurrentThread());
            boolean locked = lockInfo.lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        lockInfo.executor.shutdown();
    }
}
