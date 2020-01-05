package com.dmslob.generic;

import java.util.*;

// Arrays and generics do not mix well.
// None of these array creation expressions are legal: new List<E>[], new List<String>[], new E[].
// All will result in generic array creation errors at compile time.
public class GenericCollectionInsteadOfArray {

    public static void main(String[] args) {
        GenericCollectionInsteadOfArray collectionInsteadOfArray = new GenericCollectionInsteadOfArray();
        collectionInsteadOfArray.arraysVsCollections();
    }

    public void arraysVsCollections() {
        // Fails at runtime!
        Object[] objectArray = new Long[1];
        //objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

        // Won't compile!
        //List<Object> ol = new ArrayList<Long>(); // Incompatible types
        List<Object> objects = new ArrayList<Object>(); // Incompatible types
        objects.add("one");
        objects.add("two");
        objects.add(new Long(12));

        //String numberFromList = objects.get(0); // compile error
        Object numberFromList = objects.get(2);
        System.out.println(numberFromList);

        //List<String>[] stringLists = new List<String>[1]; // (1) Generic array creation
        //arrayOfLists(stringLists);
    }

    private void arrayOfLists(List<String>[] arrayParam) {
        List<Integer> intList = Arrays.asList(42); // (2)
        Object[] objects = arrayParam; // (3)
        objects[0] = intList; // (4)
        String s = arrayParam[0].get(0);
    }

    // List-based generic reduction
    public <E> E reduce(List<E> list, Function<E> f, E initVal) {
        List<E> snapshot;
        synchronized (list) {
            snapshot = new ArrayList<E>(list);
        }
        E result = initVal;
        for (E e : snapshot) {
            result = f.apply(result, e);
        }
        return result;
    }

    public <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> resultSet = new HashSet<>(s1);
        resultSet.addAll(s2);
        return resultSet;
    }
}

interface Function<T> {
    T apply(T arg1, T arg2);
}
