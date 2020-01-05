package com.dmslob.base;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 30.03.2017.
 */
public class ScheduledExecutorTest {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        //отложить выполнение на 5 сек
        service.schedule(new Runnable() {
            public void run() {
                System.out.println("Run...");
            }
        }, 5, TimeUnit.SECONDS);

        // выполнение каждую секунду
        ScheduledExecutorService service2 = Executors.newSingleThreadScheduledExecutor();
        service2.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("Run...");
            }
        }, 0, 1, TimeUnit.SECONDS);

        // выполнение с промежутком
        ScheduledExecutorService service3 = Executors.newSingleThreadScheduledExecutor();
        service3.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("Run...");
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
