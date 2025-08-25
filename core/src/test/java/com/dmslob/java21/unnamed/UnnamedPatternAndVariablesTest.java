package com.dmslob.java21.unnamed;

import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

// allows us to reduce boilerplate code when side effects are our only concern.
public class UnnamedPatternAndVariablesTest {

    record Car(String name) {
    }

    @Test
    public void should_unnamed_variables_for_loop() {
        // given
        var cars = List.of(new Car("BMW"), new Car("Audi"));
        // Enhanced For Loop
        for (var _ : cars) {
            // some work to do
        }
    }

    Car removeThreeCarsAndReturnFirstRemoved(Queue<Car> cars) {
        var car = cars.poll();
        var _ = cars.poll();
        var _ = cars.poll();
        return car;
    }

    @Test
    public void should_test_unnamed_variables_for_statements() {
        // Assignment Statements
        var carQueue = new SynchronousQueue<Car>();
        var firstRemoved = removeThreeCarsAndReturnFirstRemoved(carQueue);
        System.out.println(firstRemoved.name());
    }

    void someOperationThatFails(Car car) {
        throw new RuntimeException("Api error");
    }

    @Test
    public void should_unnamed_variables_for_try_catch() {
        var car = new Car("BMW");
        try {
            someOperationThatFails(car);
        } catch (IllegalStateException _) {
            System.out.println(STR."Got an illegal state exception for: \{car.name()}");
        } catch (RuntimeException _) {
            System.out.println("Got a runtime exception!");
        }
    }


    // Try-With Resources
    static class Transaction implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("Closed!");
        }
    }

    void obtainTransactionAndUpdateCar(Car car) {
        try (var _ = new Transaction()) {
            // updateCar(car);
        }
    }

    // Lambda Parameters
    Map<String, List<Car>> getCarsByFirstLetter(List<Car> cars) {
        Map<String, List<Car>> carMap = new HashMap<>();
        cars.forEach(car -> carMap.computeIfAbsent(
                        car.name().substring(0, 1),
                        _ -> new ArrayList<>()
                ).add(car)
        );
        carMap.forEach((_, _) -> System.out.println("Works!"));
        return carMap;
    }


    // Unnamed Patterns
    // They address an issue quite apparent:
    // we usually don’t need every field in records we deconstruct.
    abstract class Engine { }
    class GasEngine extends Engine { }
    class ElectricEngine extends Engine { }
    class HybridEngine extends Engine { }
    public record SportCar<T extends Engine>(String name, String color, T engine) { }

    static String getObjectsColorWithUnnamedPattern(Object object) {
        if (object instanceof SportCar(_, String color, _)) {
            return color;
        }
        return "No color!";
    }

    // Switch Patterns
    String getObjectsColorWithSwitchAndUnnamedPattern1(Object object) {
        return switch (object) {
            case SportCar(_, String color, _) -> color;
            default -> "No color!";
        };
    }
    String getObjectsColorWithSwitchAndUnnamedPattern2(Object object) {
        return switch (object) {
            case SportCar(_, _, GasEngine _) -> "gas";
            case SportCar(_, _, ElectricEngine _) -> "electric";
            case SportCar(_, _, HybridEngine _) -> "hybrid";
            default -> "none";
        };
    }

}
