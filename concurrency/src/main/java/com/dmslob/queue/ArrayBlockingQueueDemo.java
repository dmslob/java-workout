package com.dmslob.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        // create BlockingBuffer to store ints
        Buffer sharedLocation = new BlockingBuffer();

        exec.execute(new Producer(sharedLocation));
        exec.execute(new Consumer(sharedLocation));

        exec.shutdown();
    }
}

class Producer implements Runnable {

    private final static Random generator = new Random();
    private final Buffer sharedLocation;

    public Producer(Buffer shared) {
        sharedLocation = shared;
    }

    // store values from 1 to 10 in sharedLocation
    public void run() {
        int sum = 0;
        for (int count = 1; count <= 10; count++) {
            try // sleep 0 to 3 seconds, then place value in Buffer
            {
                Thread.sleep(generator.nextInt(3000)); // random sleep
                sharedLocation.set(count);
                sum += count;
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.out.println("Producer done producing\nTerminating Producer");
    }
}

class Consumer implements Runnable {

    private final static Random generator = new Random();
    private final Buffer sharedLocation; // reference to shared object

    public Consumer(Buffer shared) {
        sharedLocation = shared;
    }

    // read sharedLocation's value 10 times and sum the values
    public void run() {
        int sum = 0;

        for (int count = 1; count <= 10; count++) {
            // sleep 0 to 3 seconds, read value from buffer and add to sum
            try {
                Thread.sleep(generator.nextInt(3000));
                sum += sharedLocation.get();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        System.out.printf("\n%s %d\n%s\n", "Consumer read values totaling",
                sum, "Terminating Consumer");
    }
}

interface Buffer {

    void set(int value) throws InterruptedException;

    int get() throws InterruptedException;
}

class BlockingBuffer implements Buffer {

    private final ArrayBlockingQueue<Integer> buffer;

    public BlockingBuffer() {
        buffer = new ArrayBlockingQueue<Integer>(1);
    }

    public void set(int value) throws InterruptedException {
        buffer.put(value);
        System.out.printf("%s%2d\t%s%d\n", "Producer writes ", value,
                "Buffer cells occupied: ", buffer.size());
    }

    public int get() throws InterruptedException {
        int readValue = buffer.take();
        System.out.printf("%s %2d\t%s%d\n", "Consumer reads ",
                readValue, "Buffer cells occupied: ", buffer.size());

        return readValue;
    }
}
