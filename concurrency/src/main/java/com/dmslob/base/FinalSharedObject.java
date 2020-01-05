package com.dmslob.base;

import java.util.ArrayList;
import java.util.List;

public class FinalSharedObject {

    public static void main(String[] args) {
        final List<Human> humans = new ArrayList<>();
        humans.add(new Human(18, "Bob"));
        humans.add(new Human(23, "Jonny"));

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            humans.get(0).setAge(23);
            System.out.println("Age -> " + humans.get(0).getAge());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            humans.get(0).setAge(21);
            System.out.println("Age -> " + humans.get(0).getAge());
        }).start();

        for (int i = 0; i < 20; i++) {
            final int age = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                humans.get(0).setAge(age);
                System.out.println("Age -> " + humans.get(0).getAge());
            }).start();
        }
        new Thread(new TestRun(humans)).start();
    }

    public static void testArray() {
        final int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        Thread one = new Thread() {
            public void run() {
                //synchronized (arr) {
                arr[0] = 7;
                //}
            }
        };
        Thread two = new Thread() {
            public void run() {
                //synchronized (arr) {
                arr[0] = 5;
                //}
            }
        };
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {

        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

class TestRun implements Runnable {
    private List<Human> humans;

    TestRun(List<Human> humans) {
        this.humans = humans;
    }

    @Override
    public void run() {
        humans.get(1).setAge(12);
        System.out.println("Age [1] -> " + humans.get(1).getAge());
    }
}

class Human {
    private int age;
    private String name;

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
