package com.dmslob.ocp.innerclass;

public abstract class RectangleClass {
    public abstract double getX();

    public abstract double getY();

    public abstract double getWidth();

    public abstract double getHeight();

    public static class Double extends RectangleClass {
        private double x, y, width, height;

        public Double(double x, double y, double width, double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public double getWidth() {
            return width;
        }

        @Override
        public double getHeight() {
            return height;
        }
    }

    public static class Float extends RectangleClass {
        private float x, y, width, height;

        public Float(float x, float y, float width, float height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public double getWidth() {
            return width;
        }

        @Override
        public double getHeight() {
            return height;
        }
    }

    private RectangleClass() {
    }

    public boolean contains(double x, double y) {
        return (x >= getX() && x < getX() + getWidth()) &&
                (y >= getY() && y < getY() + getHeight());
    }
}
