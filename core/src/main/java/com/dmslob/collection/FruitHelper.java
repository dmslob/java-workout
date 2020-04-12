package com.dmslob.collection;

import java.util.Collection;

public class FruitHelper {
    public void eatAll(Collection<? extends Fruit> fruits) {
    }

    public void addApple(Collection<? super Apple> apples) {
    }
}

class Shoe {
}

class IPhone {
}

interface Fruit {
}

class Apple implements Fruit {
}

class Banana implements Fruit {
}

class GrannySmith extends Apple {
}
