package com.dmslob.oop.inheritance;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by Dmytro_Slobodenyuk on 9/12/2018.
 */
public class Main {

    public static void main(String[] args) {

        Parent child = new Child();
        System.out.println("child A=" + child.getA());

        InstrumentedHashSet<String> set = new InstrumentedHashSet<String>();
        set.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(set.getAddCount()); // expected:<3> but was:<6>

        new Stranger().stealSecret();

    }
}

@interface Test {
    String name();
}

class TT implements Test {

    @Override
    public Class<? extends Annotation> annotationType() {
        return TT.class;
    }

    @Override
    public String name() {
        return "TT";
    }
}

class InstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    public int getAddCount() {
        return addCount;
    }

    @Override
    public boolean add(E a) {
        addCount += 1;
        return super.add(a);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}

class Father {

    String parentName = "Parent";

    private String secret = "Father's secret";

    protected String getSecret() {
        return secret;
    }
}

class Son extends Father {

    String sonName = "Son";

    public void keepSecret() {
        System.out.println("I keep Secret");
    }
}

class Stranger extends Son {

    public void stealSecret() {
        System.out.println(getSecret());
    }
}

class Parent {

    static {
        System.out.println("static parent");
    }

    public Parent() {
        System.out.println("parent constructor");
    }

    int a = callme();

    int getA() {
        return a;
    }

    int callme() {
        return 42;
    }
}

class Child extends Parent {

    static {
        System.out.println("static child");
    }

    public Child() {
        System.out.println("child constructor");
    }

    int callme() {
        return 123;
    }
}