package com.dmslob.base;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Animal>> results = new ArrayList<Future<Animal>>();

        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }

        for (Future<Animal> fs : results) {
            try { // Вызов get() блокируется до завершения;
                Animal animal = fs.get();
                System.out.println(animal.getName());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        }
    }
}

class TaskWithResult implements Callable<Animal> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public Animal call() {
        Animal animal = new Animal("Bob -> " + id);
        return animal;
    }
}

class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
