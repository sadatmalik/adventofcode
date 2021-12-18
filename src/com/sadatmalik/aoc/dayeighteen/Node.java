package com.sadatmalik.aoc.dayeighteen;

public class Node {
    Node parent;
    Node left;
    Node right;
    int value;
    int depth;

    Node() {
        this.left = null;
        this.right = null;
        this.value = -1;
        this.depth = 0;
    }

    Node(int depth) {
        this();
        this.depth = depth;
    }
}
