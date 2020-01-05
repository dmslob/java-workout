package com.dmslob.generic;

public abstract class Parent<T> {
    abstract T x();
}

// When compiling a class or interface that extends a parameterized class or implements a parameterized interface,
// the compiler may need to create a synthetic method, called a bridge method, as part of the type erasure process.
// You normally don't need to worry about bridge methods, but you might be puzzled if one appears in a stack trace
class Node<T> {

    public T data;

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

// Indeed it will be
//class Node {
//
//    public Object data;
//
//    public Node(Object data) {
//        this.data = data;
//    }
//
//    public void setData(Object data) {
//        System.out.println("Node.setData");
//        this.data = data;
//    }
//}