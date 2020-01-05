package com.dmslob.generic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Base {

    public static void main(String[] args) {

        Holder ch = new CameraHolder();
        ch.save(new Camera("Sony"));
        ch.save(new Camera("Cannon"));
        System.out.println(ch.get());

        List<String> strings = new ArrayList<>();
        //List<Object> objects = strings; // compile error

        drawbacks();

        List<Integer> integers = new ArrayList<>();
        Integer ints = getFirst(integers);

        //List<String>[] lsa = new List<String>[10]; // illegal
        List<String>[] lsa = null;
        Object[] oa = lsa;  // OK because List<String> is a subtype of Object
        List<Integer> li = new ArrayList<>();
        li.add(new Integer(3));
        oa[0] = li;
        String s = lsa[0].get(0);
    }

    public static <T> T getFirst(List<T> pList) {
        T first = null;
        for (int i = 0; i < pList.size(); i++) {
            if (i == 0) {
                first = pList.get(i);
            }
        }
        return first;
    }

    static void testBoxGeneric() {
        // Both are the same
        BoxGeneric boxGeneric = new BoxGeneric();
        BoxGeneric<Object> box = new BoxGeneric();

        BoxGeneric<Integer> integer = new BoxGeneric<>();
        integer.setItem(5);
        //integer.setItem("test"); // compile error

        BoxGeneric<String> str = new BoxGeneric<>();
        str.setItem("five");

        System.out.println(integer.equals(str)); //false
        System.out.println(integer.getClass().equals(str.getClass())); //true
    }

    static void drawbacks() {
        List strList = new ArrayList();
        strList.add("some text");

        strList.add(new Integer(0));
        String str = (String) strList.get(0);
        System.out.println(str);

        Integer i = (Integer) strList.get(0); // java.lang.ClassCastException
        // but
        List<String> strings = new ArrayList<>();
        strings.add("some text");
        //strings.add(new Integer()); // compile error
        String s = strings.get(0);
        //Integer i = strings.get(0); // compile error
    }
}

class MyArrayList<V> {

    private static final int DEFAULT_SIZE = 10;

    private V[] backingArray;

    public MyArrayList() {
        //backingArray = new V[DEFAULT_SIZE]; // illegal
    }
}

class BoxGeneric<T> {
    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T o) {
        item = o;
    }
}

abstract class Product {
    protected String name;
}

class Camera extends Product {
    Camera(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "name='" + name + '\'' +
                '}';
    }
}

interface Holder<T extends Product> {
    void save(T t);

    List<T> get();
}

class CameraHolder implements Holder<Camera> {
    private List<Camera> cameras = new LinkedList<>();

    @Override
    public void save(Camera camera) {
        cameras.add(camera);
    }

    @Override
    public List<Camera> get() {
        return cameras;
    }
}

