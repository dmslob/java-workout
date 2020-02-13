package com.dmslob.threads;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            results.add(executorService.submit(new TaskWithResult(i)));
        }

        for (Future<String> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
            } finally {
                executorService.shutdown();
            }
        }
    }
}

class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int _id) {
        id = _id;
    }

    public String call() {
        return "result: " + id;
    }
}