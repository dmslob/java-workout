package com.dmslob;

public class QueueRing<E> {

    private int maxSize;
    private E[] a;
    private int currSize;
    private int head;
    private int tail;

    public QueueRing(int s) {
        maxSize = s;
        a = (E[]) new Object[maxSize];
        currSize = 0;
        head = 0;
        tail = -1;
    }

    public void clear() {
        currSize = 0;
    }

    public boolean isEmpty() {
        return currSize == 0;
    }

    public boolean isFull() {
        return currSize == maxSize;
    }

    public int getCount() {
        return currSize;
    }

    public E peek() {
        return a[head];
    }

    public void add(E c) {
        if (tail == maxSize - 1)
            tail = -1;
        a[++tail] = c;
        currSize++;
    }

    public E extract() {
        E temp = a[head++];
        if (head == maxSize)
            head = 0;
        currSize--;
        return temp;
    }
}
