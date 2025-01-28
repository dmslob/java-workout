package com.dmslob.generics;

import java.util.ArrayList;
import java.util.List;

class Fruit {
}

class Apple extends Fruit {
}

class Jonathan extends Apple {
}

class Orange extends Fruit {
}

public class CovariantArrays {

    public static void main(String[] args) {
        Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple(); // OK
        fruits[1] = new Jonathan(); // OK
        System.out.println(fruits.getClass());
        // Runtime type is Apple[], not Fruit[] or Orange[]:
        try {
            // Compiler allows you to add Fruit:
            fruits[0] = new Fruit(); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            // Compiler allows you to add Oranges:
            fruits[0] = new Orange(); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }

        //Compile Error: incompatible types:
        //List<Fruit> fruitList = new ArrayList<Apple>();

        // Wildcards allow covariance:
        List<? extends Fruit> fruitList = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        // fruitList.add(new Apple());
        // fruitList.add(new Fruit());
        // fruitList.add(new Object());
        fruitList.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit:
        Fruit fruit = fruitList.get(0);
    }
}