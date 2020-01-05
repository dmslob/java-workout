package com.dmslob.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Foo {

    public List<Foo> getListWithMeIncluded() {
        return new ArrayList<Foo>() {{
            add(Foo.this);
        }};
    }

    public static void main(String... args) {
        collections();
        Foo foo = new Foo();
        List<Foo> fooList = foo.getListWithMeIncluded();
        System.out.println(foo.equals(fooList.get(0)));

        String s1 = "s1";
        ArrayList<String> foos = new ArrayList<String>() {{ // Mutable List
            add(s1);
        }};
        System.out.println(foos.toString());

        foos.add("s2");
        System.out.println(foos.toString());

        List<Integer> integers = Arrays.asList(1, 2, 3); // Immutable List
        //integers.add(4); //java.lang.UnsupportedOperationException
    }

    static void collections() {
        A a1 = new B();
        a1.setOrder("1");
        a1.setPolicy("123");

        B b1 = new B();
        b1.setOrder("1");
        b1.setPolicy("124");
        List<A> as = new ArrayList<>();
        as.add(a1);
        as.add(b1);

        as.forEach(a -> System.out.println(a.getPolicy()));
    }
}
class A {
    private String order;
    private String policy;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
}

class B extends A {
    private String effDate;

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }
}