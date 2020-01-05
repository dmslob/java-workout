package com.dmslob.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class FolderProcessorDemo {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //Create three FolderProcessor tasks. Initialize each one with a different folder path.
        FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
        FolderProcessor apps = new FolderProcessor("C:\\Program Files", "log");
        FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");

        //Execute the three tasks in the pool using the execute() method.
        forkJoinPool.execute(system);
        forkJoinPool.execute(apps);
        forkJoinPool.execute(documents);

        // Write to the console information about the status of the pool every second
        // until the three tasks have finished their execution.
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", forkJoinPool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", forkJoinPool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", forkJoinPool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", forkJoinPool.getStealCount());
            System.out.printf("******************************************\n");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));

        forkJoinPool.shutdown();
        List<String> taskResults;

        taskResults = system.join();
        System.out.printf("System: %d files found.\n", taskResults.size());

        taskResults = apps.join();
        System.out.printf("Apps: %d files found.\n", taskResults.size());

        taskResults = documents.join();
        System.out.printf("Documents: %d files found.\n", taskResults.size());
    }
}
