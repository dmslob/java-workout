package com.dmslob.generic;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Covariance: ? extends Fruit (or Upper Bound Wildcard)
 * Contravariance: ? super Fruit (or Lower Bound Wildcard)
 * Invariance: neither covariant nor contravariant
 * Remark:
 *  covariance is read-only
 *  contravariance is write-only
 *  otherwise compile-time error
 *
 * Use ? extends T wildcard if you need to retrieve object of type T from a collection.
 * Use ? super T wildcard if you need to put objects of type T in a collection.
 */
public class PecsTest {

    @Test
    public void should_produce_items() {
        // given
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit(100));

        List<Plum> plums = new ArrayList<>();
        plums.add(new Plum(100));

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(80));

        List<AsianApple> asianApples = new ArrayList<>();
        asianApples.add(new AsianApple(120));

        // when
        int fruitsWeight = calculateWeight(fruits);
        int plumsWeight = calculateWeight(plums);
        int applesWeight = calculateWeight(apples);
        int asianApplesWeight = calculateWeight(asianApples);

        // then
        assertThat(fruitsWeight).isEqualTo(100);
        assertThat(plumsWeight).isEqualTo(100);
        assertThat(applesWeight).isEqualTo(80);
        assertThat(asianApplesWeight).isEqualTo(120);
    }

    private int calculateWeight(List<? extends Fruit> fruits) {
        int sum = 0;
        for (Fruit f : fruits) {
            sum = sum + f.getWeight();
        }
        Fruit fruit = fruits.get(0);
        //fruits.add(new Fruit(200));
        //fruits.add(new Apple(200));
        //fruits.add(new AsianApple(150));
        //fruits.add(null);
        return sum;
    }

    @Test
    public void should_consume_items() {
        // given
        List<Fruit> fruits = new ArrayList<>();
        List<Apple> apples = new ArrayList<>();
        List<AsianApple> asianApples = new ArrayList<>();
        List<Plum> plums = new ArrayList<>();

        // when
        fill(fruits);
        fill(apples);
        //fill(asianApples);
        //fill(plums);
    }

    private void fill(List<? super Apple> apples) {
        apples.add(new Apple(100));
        apples.add(new AsianApple(150));
        //apples.add(new Fruit(120));
        //apples.add(new Plum(70));
        System.out.println(apples);

        //Apple apple = (Apple) apples.get(0);
        Object o = apples.get(0);
    }
}

class Fruit {
    private final int weight;

    public Fruit(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "weight=" + weight +
                '}';
    }
}

class Apple extends Fruit {

    public Apple(int weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + getWeight() +
                '}';
    }
}

class AsianApple extends Apple {

    public AsianApple(int weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "AsianApple{" +
                "weight=" + getWeight() +
                '}';
    }
}

class Plum extends Fruit {

    public Plum(int weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "Plum{" +
                "weight=" + getWeight() +
                '}';
    }
}