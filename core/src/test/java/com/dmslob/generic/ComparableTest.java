package com.dmslob.generic;

import org.testng.annotations.Test;

public class ComparableTest {
    @Test
    public void should_f() {
        // given
        Class1 class1 = new Class1();
        Class2 class2 = new Class2();

        // when | then
        int a = class1.compareTo(class2);
        // compile error: Required type Class2, provided Class1
        // class2.compareTo(class1);
    }
}

class Class1 implements Comparable {
    public int compareTo(Class1 o) {
        return 0;
    }

    public int compareTo(Object o) {
        return 0;
    }
}

class Class2 implements Comparable<Class2> {
    public int compareTo(Class2 o) {
        return 0;
    }

    // compile error
//    public int compareTo(Object o) {
//        return 0;
//    }
}
