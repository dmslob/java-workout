package com.dmslob.generics;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VarianceTest {

    void doInvariance(List<Predator> predators) {
        // Only Predator
        Predator predator = predators.getFirst();
    }

    @Test
    void invariance_test() {
        // given
        List<Animal> animalList = new ArrayList<>();
        List<Mammal> mammalList = new ArrayList<>();
        List<Predator> predatorList = new ArrayList<>();
        List<Lion> lionList = new ArrayList<>();
        List<AfricanLion> africanLionList = new ArrayList<>();
        // when | then
        List<Predator> predatorList1 = predatorList;
        // Compile error - Required type List<Predators>
        //List<Predator> predatorList2 = animalList;
        //List<Predator> predatorList3 = mammalList;
        //List<Predator> predatorList4 = lionList;
        //List<Predator> predatorList5 = africanLionList;

        doInvariance(predatorList);
        // Compile error - Required type List<Predators>
        //doInvariance(animalList);
        //doInvariance(mammalList);
        //doInvariance(lionList);
        //doInvariance(africanLionList);
    }

    void doCovariance(List<? extends Predator> predators) {
        Object o = predators.getFirst();         // Ok
        Animal animal = predators.getFirst();    // Ok
        Mammal mammal = predators.getFirst();    // Ok
        Predator predator = predators.getFirst();
        // Compile error
        //Lion lion = predators.get(0);
        //AfricanLion africanLion = predators.get(0);
        // Compile error
        //predators.add(new Animal());
        //predators.add(new Mammal());
        //predators.add(new Predator());
        //predators.add(new Lion());
        //predators.add(new AfricanLion());
    }

    @Test
    void covariance_test() {
        // given
        List<Animal> animalList = new ArrayList<>();
        List<Mammal> mammalList = new ArrayList<>();
        List<Predator> predatorList = new ArrayList<>();
        List<Lion> lionList = new ArrayList<>();
        List<AfricanLion> africanLionList = new ArrayList<>();
        // when | then
        // Compile error - Required type List<? extends Predators>
        //doCovariance(animalList);
        //doCovariance(mammalList);
        doCovariance(predatorList);      // Ok
        doCovariance(lionList);          // Ok
        doCovariance(africanLionList);   // Ok
    }

    void doContravariance(List<? super Predator> predators) {
        Object a = predators.getFirst();        // Ok
        // Compile error
        //Animal animal = predators.get(0);
        //Mammal mammal = predators.get(0);
        //Predator predator = predators.get(0);
        //Lion lion = predators.get(0);
        //AfricanLion africanLion = predators.get(0);

        //Compile error
        //predators.add(new Animal());
        //predators.add(new Mammal());
        // Ok
        predators.add(new Predator());
        predators.add(new Lion());
        predators.add(new AfricanLion());
    }

    @Test
    void contravariance_test() {
        // given
        List<Animal> animalList = new ArrayList<>();
        List<Mammal> mammalList = new ArrayList<>();
        List<Predator> predatorList = new ArrayList<>();
        List<Lion> lionList = new ArrayList<>();
        List<AfricanLion> africanLionList = new ArrayList<>();
        // when | then
        doContravariance(animalList);    // Ok
        doContravariance(mammalList);    // Ok
        doContravariance(predatorList);  // Ok
        // Compile error - Required type List<? super Predators>
        //doContravariance(lionList);
        //doContravariance(africanLionList);
    }
}

class Animal {
}

class Mammal extends Animal {
}

class Predator extends Mammal {
}

class Lion extends Predator {
}

class AfricanLion extends Lion {
}