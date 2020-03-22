package com.dmslob.optional;

import java.util.Optional;
import java.util.Properties;

public class OptionalDemo {

    public static void main(String[] args) {
        OptionalDemo demo = new OptionalDemo();
        //demo.noElementException();
        //demo.nullPointerException();
        demo.getElement();
        demo.empty();
        demo.orElse();
        demo.nullable();
        demo.property();
    }

    void noElementException() {
        Optional<Car> optionalCar = Optional.empty();
        optionalCar.get();
    }

    void nullPointerException() {
        Car mazda = null;
        Optional<Car> optionalCar = Optional.of(mazda);
    }

    void getElement() {
        Car honda = new Car("Honda");
        Optional<Car> optHonda = Optional.of(honda);
        System.out.println(optHonda.get().getName());
    }

    void empty() {
        Optional<Car> optionalCar = Optional.empty();
        if (optionalCar.isPresent()) {
            System.out.println(optionalCar.get());
        } else {
            System.out.println("Empty");
        }
    }

    void orElse() {
        Car anotherHonda = new Car("Honda CRV");
        Optional<Car> optionalCar = Optional.empty();

        Car carValue = optionalCar.orElse(anotherHonda);
        System.out.println(carValue.getName());
    }

    void nullable() {
        Car bmw = null;
        Optional<Car> optionalCar = Optional.ofNullable(bmw);
        optionalCar.ifPresent(car -> System.out.println(car.getName()));

        Insurance insurance = new Insurance("Company");
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);
        System.out.println(name.get());
    }

    void property() {
        String name = "A";
        Properties props = new Properties();
        Optional<String> property = Optional.ofNullable(props.getProperty(name));
        System.out.println("isPresent: " + property.isPresent());
    }
}
