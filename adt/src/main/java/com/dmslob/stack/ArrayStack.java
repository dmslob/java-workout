package com.dmslob.stack;

public class ArrayStack<E> {
    private static final int CAPACITY = 1000;
    private E[] elements;
    private int top = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(5);
        stack.push(3);

        System.out.println(stack.size()); // contents: (5, 3) outputs 2
        System.out.println(stack.pop()); // contents: (5) outputs 3
        System.out.println(stack.isEmpty()); // contents: (5) outputs false
        System.out.println(stack.pop()); // contents: () outputs 5
        System.out.println(stack.isEmpty()); // contents: () outputs true
        System.out.println(stack.pop()); // contents: () outputs null
    }

    public int size() {
        return (top + 1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void push(E e) throws IllegalStateException {
        if (size() == elements.length) {
            throw new IllegalStateException("Stack is full");
        }
        elements[++top] = e;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return elements[top];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E element = elements[top];
        elements[top] = null; // dereference to help garbage collection
        top--;
        return element;
    }
}
