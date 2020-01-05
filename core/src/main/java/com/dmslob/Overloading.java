package com.dmslob;

import java.math.BigInteger;
import java.util.*;

public class Overloading {

    public static void main(String[] args) {
        Executor executor = new Executor();
        // 1. Widening (1 -> int, 1.0 -> double, Double -> Object)
        // 2. Boxing
        // 3. varargs
        executor.executeAction(1);
        //executor.executeAction(1.0);
        //executor.executeAction(Double.valueOf("5"));
        //executor.executeAction(1L);

        Double d = 2.4;
        new AutoboxingOverloadTest().doWork(d);

        CollectionClassifier.testClassify();
    }
}

class OverloadingWithObjects {

    void f(Object o1, int i) {}

    void f(Object o1, Object o2) {}

    void test() {
        double d = 1.0;

        int i = 1;
        //f(d, i); // ERROR!  Ambiguous
    }
}

class AutoboxingOverloadTest {

    void doWork(Object o) {
        System.out.println("Object: " + o.toString());
    }

    void doWork(double o) {
        System.out.println("Double: " + o);
    }
}

class Executor {

    // always last
    void executeAction(int... var) {
        System.out.println("varargs");
    }

    void executeAction(Integer var) {
        System.out.println("Integer");
    }

    void executeAction(Object var) {
        System.out.println("Object");
    }

    void executeAction(short var) {
        System.out.println("short");
    }

    void executeAction(float var) {
        System.out.println("float");
    }

    void executeAction(double var) {
        System.out.println("double");
    }
}

class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "Set";
    }

    public static String classify(List<?> list) {
        return "List";
    }

    public static String classify(Collection<?> collection) {
        return "Unknown Collection";
    }

    public static void testClassify() {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection c : collections) {
            System.out.println(classify(c));
        }
    }
}
