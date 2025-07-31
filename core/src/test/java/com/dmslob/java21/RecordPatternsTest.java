package com.dmslob.java21;

import org.testng.annotations.Test;

public class RecordPatternsTest {

    record Point(Integer x, Integer y) {
    }

    void printSum_prior_java16(Object obj) {
        if (obj instanceof Point) {
            Point p = (Point) obj;
            int x = p.x();
            int y = p.y();
            System.out.println(x + y);
        }
    }

    void printSum_java16(Object obj) {
        if (obj instanceof Point p) {
            int x = p.x();
            int y = p.y();
            System.out.println(x + y);
        }
    }

    void printSum_java21(Object obj) {
        if (obj instanceof Point(Integer x, Integer y)) {
            System.out.println(x + y);
        } else {
            System.out.println("not a point");
        }
    }

    @Test
    public void should_print_sum_when_Point() {
        // given
        var point = new Point(10, 20);
        // when
        printSum_java21(point);
        // given
        record Point(double x, double y) {
        }
        var p = new Point(10d, 20d);
        // when
        printSum_java21(p);
    }

    enum Color {RED, GREEN, BLUE}

    record ColoredPoint(Point p, Color c) {
    }

    record Rectangle(ColoredPoint upperLeft, ColoredPoint lowerRight) {
    }

    void printUpperLeftColoredPoint(Rectangle r) {
        if (r instanceof Rectangle(ColoredPoint ul, ColoredPoint lr)) {
            System.out.println(ul.c());
            System.out.println(lr.c);
        }
    }

    static void printXCoordOfUpperLeftPointWithPatterns(Rectangle r) {
        if (r instanceof Rectangle(
                ColoredPoint(Point(var x, var y), var c),
                var lr
        )) {
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            System.out.println("c: " + c);
        }
    }

    void printColorOfUpperLeftPoint(Rectangle r) {
        if (r instanceof Rectangle(
                ColoredPoint(Point p, Color c),
                ColoredPoint lr
        )) {
            System.out.println(p);
            System.out.println(c);
            System.out.println(lr);
        }
    }

    @Test
    public void should_print_sum_when_Rectangle() {
        // given
        var ul = new ColoredPoint(new Point(1, 2), Color.RED);
        var lr = new ColoredPoint(new Point(2, 1), Color.BLUE);
        var rectangle = new Rectangle(ul, lr);
        // when & then
        printUpperLeftColoredPoint(rectangle);
        printColorOfUpperLeftPoint(rectangle);
    }

    @Test
    public void should_match_Pair_when_objects_args() {
        // As of Java 21
        //record Pair(var x, var y) {} // Compile error
        record Pair(Object x, Object y) {}
        var pInts = new Pair(42, 42);
        var pStrings = new Pair("1", "2");

        //if (pInts instanceof Pair(int s, int t)) //Compile error
        if (pInts instanceof Pair(Integer s, Integer t)) {
            System.out.println(s + ", " + t);
        } else {
            System.out.println("Not a pair of strings");
        }
    }

    // As of Java 21
    record MyPair<S, T>(S fst, T snd) {
    }

    <F, S> void recordInference(MyPair<F, S> pair) {
        switch (pair) {
            case MyPair(String f, Integer s) -> {
                System.out.printf("1: [%s, %s]", f, s);
            }
            case MyPair(Integer f, Integer s) -> {
                System.out.printf("2: [%s, %s]", f, s);
            }
            case MyPair(var f, var s) -> {
                System.out.printf("3: [%s, %s]", f, s);
            }
            case null -> System.out.println("null");
            default -> System.out.println(pair);
        }
    }

    @Test
    public void should_test_recordInference() {
        // given
        var mp1 = new MyPair<String, Integer>("1", 2);
        var mp2 = new MyPair<Integer, Integer>(1, 2);
        var mp3 = new MyPair<Double, Double>(1d, 2d);
        // when
        recordInference(mp1);
    }

    // As of Java 21
    record Box<T>(T t) {}

    @Test
    public void should_test_nested_record_patterns() {
        // given
        var b = new Box<String>("test");
        var bbs = new Box<Box<String>>(b);
        // when
        if (bbs instanceof Box<Box<String>>(Box(String s))) {
            System.out.println("String " + s);
        }
        // or
        if (bbs instanceof Box<Box<String>>(Box(var s))) {
            System.out.println("String " + s);
        }
        // or
        var bi = new Box<Integer>(1);
        var bbi = new Box<Box<Integer>>(bi);
        if (bbi instanceof Box<Box<Integer>>(Box(Integer s))) {
            System.out.println("Integer " + s);
        }
    }


    //Record patterns and exhaustive switch
    class A {}
    class B extends A {}
    sealed interface I permits C, D {}
    final class C implements I {}
    final class D implements I {}
    record Pair<T>(T x, T y) {}

    @Test
    public void should_test_exhaustive_switch() {
        Pair<A> p1 = new Pair<>(new A(), new A());
        Pair<I> p2 = new Pair<>(new C(), new D());
        // not exhaustive switch
        // 'switch' statement does not cover all possible input value
//        switch (p1) {                // Error!
//            case Pair<A>(A a, B b) -> {}
//            case Pair<A>(B b, A a) -> {}
//        }
        // These two switches are exhaustive,
        // since the interface I is sealed and
        // so the types C and D cover all possible instances:
        switch (p2) {
            case Pair<I>(I i, C c) -> {}
            case Pair<I>(I i, D d) -> {}
        }
        switch (p2) {
            case Pair<I>(C c, I i) -> {}
            case Pair<I>(D d, C c) -> {}
            case Pair<I>(D d1, D d2) -> {}
        }
    }
}
