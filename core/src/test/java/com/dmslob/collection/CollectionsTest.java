package com.dmslob.collection;

import com.dmslob.collection.stream.tasks.TweeterTagsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CollectionsTest {

    private static final Logger LOGGER = LogManager.getLogger(CollectionsTest.class);

    private final List<String> topics = new ArrayList<>();
    private final List<String> newTopics = new ArrayList<>();

    @Before
    public void setUp() {
        topics.add("practice");
        topics.add("code");
        topics.add("quiz");
        topics.add("geeks for geeks");

        newTopics.add("geeks");
        newTopics.add("geek");
        newTopics.add("for");
        newTopics.add("coder");
    }

    @Test
    public void should_collect_tags() {
        List<String> tweets = new ArrayList<>();
        tweets.add("#Java and #Scala and #Scala and #Scala");
        tweets.add("#Java and #Kotlin and #Groovy");
        tweets.add("#Java and #Scala and #Kotlin");

        TweeterTagsService tagsService = new TweeterTagsService();

        Assert.assertEquals(
                "[#Scala, #Java, #Kotlin, #Groovy]",
                tagsService.findAndSortTagsByFrequency(tweets).toString()
        );
    }

    @Test
    public void joinTest() {
        topics.addAll(newTopics);
        LOGGER.info(topics.toString());

        Assert.assertEquals(8, topics.size());
    }

    @Test
    public void forEachRemainingTest() {
        List<String> apps = Arrays.asList("Skype", "Facebook", "Instagram", "Twitter");
        List<String> cars = new ArrayList<String>() {
            {
                add("BMW");
                add("Ford");
                add("Toyota");
            }
        };

        Assert.assertEquals(3, cars.size());

        cars.remove(0);
        Assert.assertEquals(2, cars.size());

        cars.remove(1);
        Assert.assertEquals(1, cars.size());
    }

    @Test
    public void disjointTest() {
        boolean isDisjoint = Collections.disjoint(topics, newTopics);
        LOGGER.info("isDisjoint={}", isDisjoint);

        Assert.assertTrue(isDisjoint);
    }

    @Test
    public void pecsExtendsTest() {
        // Covariance
        List<Integer> ints = new ArrayList<>();
        List<? extends Number> nums = ints;

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle());

        repair(vehicles);
    }

    @Test
    public void pecsSuperTest() {
        // Contravariance
        List<Number> nums = new ArrayList<>();
        List<? super Integer> ints = nums;

        List<Vehicle> vehicles = new ArrayList<>();
        accept(vehicles, new Vehicle());
        accept(vehicles, new Car());
        accept(vehicles, new BMW());
    }

    @Test
    public void pecsTest() {
        List<Number> nums = Arrays.<Number>asList(4.1F, 0.2F);
        List<Integer> ints = Arrays.asList(1, 2);
        Collections.copy(nums, ints);
        //Collections.copy(ints, nums); // Compile time error
    }

    @Test
    public void rawTypesTest() {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList arrayList = new ArrayList();
        arrayList = strings; // Ok
        strings = arrayList; // Unchecked assignment
        arrayList.add(1); //unchecked call
    }

    public void repair(List<? extends Vehicle> vehicles) {
        // repair each vehicle one by one
        Vehicle vehicle = vehicles.get(0);
        vehicles.add(null);
        //vehicles.add(new Vehicle()); //Compile time error
        //vehicles.add(new Car()); //Compile time error
        //vehicles.add(new BMW()); //Compile time error
    }

    public void accept(List<? super Vehicle> vehicles, Vehicle v) {
        // register a vehicle for repairing in garage
        vehicles.add(v);
        Vehicle vehicle = (Vehicle) vehicles.get(0);
    }
}

class Vehicle {
}

class Car extends Vehicle {
}

class BMW extends Car {
}