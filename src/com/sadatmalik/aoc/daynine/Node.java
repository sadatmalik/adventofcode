package com.sadatmalik.aoc.daynine;

public class Node {
    int height;
    Node left;
    Node right;
    Node up;
    Node down;

    int riskLevel;

    public Node(int height) {
        this.height = height;
        this.riskLevel = height + 1;
    }

    public boolean isLow() {
        if ((left == null || height < left.height)
                && (right == null || height < right.height)
                && (up == null || height < up.height)
                && (down == null || height < down.height)) {

            return true;

        }
        return false;
    }
}
