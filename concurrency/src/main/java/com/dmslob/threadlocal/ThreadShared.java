package com.dmslob.threadlocal;

public class ThreadShared {

    public static class MyRunnable implements Runnable {

        private int shared = 0;

        @Override
        public void run() {
            shared += 5;
            System.out.println("shared: " + shared);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " : " + shared);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalSample.MyRunnable sharedRunnableInstance = new ThreadLocalSample.MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance, "thread1");
        Thread thread2 = new Thread(sharedRunnableInstance, "thread2");

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }
}
