package com.dmslob.oca;

import org.apache.tools.ant.filters.EscapeUnicode;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {

        System.out.println(test(i -> i == 5));
        //System.out.println(test(i -> {i == 5;}));
        System.out.println(test((i) -> i == 5));
        //System.out.println(test((int i) -> i == 5);
        //System.out.println(test((int i) -> {return i == 5;}));
        System.out.println(test((i) -> {
            return i == 5;
        }));

        //System.out.println(LocalDate.of(2015, Calendar.APRIL, 1));
        System.out.println(LocalDate.of(2015, Month.APRIL, 1));
        // System.out.println(LocalDate.of(2015, 3, 1));
        System.out.println(LocalDate.of(2015, 4, 1));
        //System.out.println(new LocalDate(2015, 3, 1));
        //System.out.println(new LocalDate(2015, 4, 1));

        StringBuilder b1 = new StringBuilder("snorkler");
        StringBuilder b2 = new StringBuilder("yoodler");

        //System.out.println(b2.substring(2, 5));
        //System.out.println(b2.insert(3, b1.append("a")));
        System.out.println(b1.replace(3,4, b2.substring(4)));

//        b1.append(b2.substring(2, 5).toUpperCase());
//        System.out.println(b1.toString());
//        System.out.println(b2.toString());

//        b2.insert(3, b1.append("a"));
//        System.out.println(b1.toString());
//        System.out.println(b2.toString());

//        b1.replace(3,4, b2.substring(4)).append(b2.append(false));
//        System.out.println(b1.toString());
//        System.out.println(b2.toString());

        short sh = 45;
        char ch = 'a';
        sh = (short) ch;
        System.out.println(sh);
        ch = (char) sh;
        System.out.println(ch);

        Boolean b = new Boolean(null);
        System.out.println(b);
    }

    private static boolean test(Predicate<Integer> p) {
        return p.test(5);
    }
}

interface A {
    int i = 10; // public static final int i = 10;
}

class One {

    String name = "one";

    int test(int a) throws Exception {
        System.out.println("One");
        return 1;
    }
}

class Two extends One {

    public Two() {
        System.out.println("init");
    }

    public Two(String name) {
        this();
        //super();
        this.name = name;
    }

    String name = "two";

    protected int test(int a) {
        System.out.println("Two");
        return 1;
    }
}