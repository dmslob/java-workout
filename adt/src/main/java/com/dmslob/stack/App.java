package com.dmslob.stack;

public class App {

    public static void main(String[] args) throws CloneNotSupportedException {
        App app = new App();
        app.testArrayStack();
    }

    public void testArrayStack() {
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
}