package com.dmslob.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LRU (Last recently used) cache
 */
public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> storage = new HashMap<>();

    private Node<K, V> head = null;
    private Node<K, V> tail = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        if (storage.containsKey(key)) {
            Node<K, V> n = storage.get(key);
            delete(n);
            setHead(n);
            return n.value;
        }
        return null;
    }

    public V getHead() {
        return head.value;
    }

    public V getTail() {
        return tail.value;
    }

    private void delete(Node<K, V> node) {
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

    private void setHead(Node<K, V> node) {
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
        if (storage.containsKey(key)) {
            // update the old value
            Node<K, V> old = storage.get(key);
            old.value = value;
            delete(old);
            setHead(old);
        } else {
            Node<K, V> newNode = new Node<>(key, value);
            if (storage.size() >= capacity) {
                storage.remove(tail.key);
                // remove last node
                delete(tail);
                setHead(newNode);
            } else {
                setHead(newNode);
            }
            storage.put(key, newNode);
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

    @Override
    public String toString() {
        return "LRUCache{" +
                "capacity=" + capacity +
                ", storage=" + storage.entrySet() +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
}
