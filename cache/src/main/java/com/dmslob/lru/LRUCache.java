package com.dmslob.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private Map<K, Node<K, V>> cache = new HashMap<>();

    private Node<K, V> head = null;
    private Node<K, V> tail = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            Node<K, V> n = cache.get(key);
            delete(n);
            setHead(n);
            return n.value;
        }
        return null;
    }

    private void delete(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private void setHead(Node node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    public void set(K key, V value) {
        if (cache.containsKey(key)) {
            // update the old value
            Node old = cache.get(key);
            old.value = value;
            delete(old);
            setHead(old);
        } else {
            Node newNode = new Node(key, value);
            if (cache.size() >= capacity) {
                cache.remove(tail.key);
                // remove last node
                delete(tail);
                setHead(newNode);
            } else {
                setHead(newNode);
            }
            cache.put(key, newNode);
        }
    }

    private static class Node<K, V> {
        K key;
        V value;

        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
