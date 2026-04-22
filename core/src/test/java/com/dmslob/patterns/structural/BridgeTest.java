package com.dmslob.patterns.structural;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

/**
 * The Bridge pattern is a structural design pattern that decouples an abstraction from its implementation,
 * allowing the two to vary independently. It is used to separate the interface of a class from its implementation,
 * enabling them to evolve separately without affecting each other.
 */
public class BridgeTest {

    interface Color {
        String fill();
    }

    static class Red implements Color {
        @Override
        public String fill() {
            return "Filling with red color";
        }
    }

    static class Green implements Color {
        @Override
        public String fill() {
            return "Filling with green color";
        }
    }

    abstract static class Shape {
        protected Color color;

        public Shape(Color color) {
            this.color = color;
        }

        abstract public String draw();
    }

    static class Square extends Shape {
        public Square(Color color) {
            super(color);
        }

        @Override
        public String draw() {
            return "Square drawn. " + color.fill();
        }
    }

    @Test
    public void should_fill_square_with_red_color() {
        // when
        var square = new Square(new Red());
        // then
        assertEquals("Square drawn. Filling with red color", square.draw());
    }
}
