package com.dmslob.generic;

import java.util.ArrayList;

public class GenericClassExample {

    public static void main(String[] args) {
        rowTypeExample();

        double[] doubleElements = {1.1, 2.2, 3.3, 4.4, 5.5};
        int[] integerElements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Stack<Double> doubleStack = new Stack<Double>(5);
        Stack<Integer> integerStack = new Stack<Integer>();

        // doubleStack
        testPushDouble(doubleStack, doubleElements);
        testPopDouble(doubleStack);

        // integerStack
        testPushInteger(integerStack, integerElements);
        testPopInteger(integerStack);

        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        testPush("doubleStack", doubleStack, doubles);
        testPop("doubleStack", doubleStack);

        testPush("integerStack", integerStack, integers);
        testPop("integerStack", integerStack);
    }

    private static void rowTypeExample() {
        Double[] doubleElements = {1.1, 2.2, 3.3, 4.4, 5.5};
        Integer[] integerElements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Stack rawTypeStack1 = new Stack(5);

        Stack rawTypeStack2 = new Stack<Double>(5);

        // Stack of raw types assigned to Stack<Integer> variable
        Stack<Integer> integerStack = new Stack(10);

        testPush("rawTypeStack1", rawTypeStack1, doubleElements);
        testPop("rawTypeStack1", rawTypeStack1);
        testPush("rawTypeStack2", rawTypeStack2, doubleElements);
        testPop("rawTypeStack2", rawTypeStack2);
        testPush("integerStack", integerStack, integerElements);
        testPop("integerStack", integerStack);
    }

    private static void testPushDouble(Stack<Double> stack, double[] values) {
        System.out.println("\nPushing elements onto doubleStack");

        for (double value : values) {
            System.out.printf("%.1f ", value);
            stack.push(value);
        }
    }

    private static void testPopDouble(Stack<Double> stack) {
        try {
            System.out.println("\nPopping elements from doubleStack");
            double popValue;

            while (!stack.isEmpty()) {
                popValue = stack.pop();
                System.out.printf("%.1f ", popValue);
            }
        } catch (EmptyStackException emptyStackException) {
            System.err.println();
            emptyStackException.printStackTrace();
        }
    }

    private static void testPushInteger(Stack<Integer> stack, int[] values) {
        System.out.println("\nPushing elements onto integerStack");

        for (int value : values) {
            System.out.printf("%d ", value);
            stack.push(value);
        }
    }

    private static void testPopInteger(Stack<Integer> stack) {
        try {
            System.out.println("\nPopping elements from integerStack");
            int popValue;

            while (!stack.isEmpty()) {
                popValue = stack.pop();
                System.out.printf("%d ", popValue);
            }
        } catch (EmptyStackException emptyStackException) {
            System.err.println();
            emptyStackException.printStackTrace();
        }
    }

    private static <T> void testPush(String name, Stack<T> stack, T[] elements) {
        System.out.println("\nPushing elements onto " + name);

        for (T element : elements) {
            System.out.print(element + " ");
            stack.push(element);
        }
    }

    private static <T> void testPop(String name, Stack<T> stack) {
        try {
            System.out.println("\nPopping elements from " + name);
            T popValue;

            while (!stack.isEmpty()) {
                popValue = stack.pop();
                System.out.print(popValue + " ");
            }
        } catch (EmptyStackException emptyStackException) {
            System.out.println();
            emptyStackException.printStackTrace();
        }
    }
}

class EmptyStackException extends RuntimeException {
    public EmptyStackException() {
        this("Stack is empty");
    }

    public EmptyStackException(String message) {
        super(message);
    }
}

class Stack<T> {
    private ArrayList<T> elements;

    public Stack() {
        this(10);
    }

    public Stack(int capacity) {
        int initCapacity = capacity > 0 ? capacity : 10;
        elements = new ArrayList<T>(initCapacity);
    }

    public void push(T pushValue) {
        elements.add(pushValue);
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

    public T pop() {
        if (elements.isEmpty())
            throw new EmptyStackException("Stack is empty, cannot pop");

        return elements.remove(elements.size() - 1);
    }
}

