package com.dmslob.patterns.behavioral;

import org.testng.annotations.Test;

import java.util.List;

/**
 * Visitor is a behavioral design pattern that lets you separate algorithms
 * from the objects on which they operate.
 */
public class VisitorTest {

    @Test
    public void should_test_visitor() {
        // given
        List<Shape> shapes = List.of(
                new Circle(5),
                new Rectangle(3, 4));

        ShapeVisitor areaCalculator = new AreaCalculator();
        ShapeVisitor renderer = new ShapeRenderer();

        System.out.println("=== Calculating Areas ===");
        shapes.forEach(shape -> shape.accept(areaCalculator));

        System.out.println("\n=== Rendering Shapes ===");
        shapes.forEach(shape -> shape.accept(renderer));
    }

    interface ShapeVisitor {
        void visit(Circle circle);

        void visit(Rectangle rectangle);
    }

    interface Shape {
        void accept(ShapeVisitor visitor);
    }

    static class Circle implements Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class Rectangle implements Shape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        @Override
        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class AreaCalculator implements ShapeVisitor {
        @Override
        public void visit(Circle circle) {
            double area = Math.PI * Math.pow(circle.getRadius(), 2);
            System.out.printf("Circle area = %s\n", area);
        }

        @Override
        public void visit(Rectangle rectangle) {
            double area = rectangle.getWidth() * rectangle.getHeight();
            System.out.printf("Rectangle area = %s\n", area);
        }
    }

    static class ShapeRenderer implements ShapeVisitor {
        @Override
        public void visit(Circle circle) {
            System.out.printf("Rendering a circle with radius %s\n", circle.getRadius());
        }

        @Override
        public void visit(Rectangle rectangle) {
            System.out.printf("Rendering a rectangle %s * %s", rectangle.getWidth(), rectangle.getHeight());
        }
    }
}
