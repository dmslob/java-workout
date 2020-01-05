package com.dmslob.algorithms;

public class BinarySearchTree {

    public static boolean contains(Node root, int value) {
        if (root == null) {
            return false;
        }

        int compareResult = Integer.valueOf(value).compareTo(Integer.valueOf(root.value));

        if (compareResult < 0) {
            return contains(root.left, value);
        } else if (compareResult > 0) {
            return contains(root.right, value);
        } else {
            return true;    // Match
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);

        System.out.println(contains(n2, 3));
    }
}

class Node {

    int value;
    Node left, right;

    Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
