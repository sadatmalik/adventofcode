package com.sadatmalik.aoc.dayeleven;

import com.sadatmalik.aoc.daynine.BasinSize;

import java.util.ArrayList;

public class Node {
    int energyLevel;

    Node left;
    Node right;
    Node up;
    Node down;

    //diagonals
    Node up_left;
    Node up_right;
    Node down_left;
    Node down_right;

    BasinSize basin;

    public Node(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public boolean isReadyToFlash() {
        return energyLevel == 9;
    }

    public void flash() {
        ArrayList<Node> nodes = getNodes();
        for (Node node : nodes) {
            node.energyLevel++;
        }
    }

    private ArrayList<Node> getNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        if (up != null) {
            nodes.add(up);
        }
        if (down != null) {
            nodes.add(down);
        }
        if (left != null) {
            nodes.add(left);
        }
        if (right != null) {
            nodes.add(right);
        }
        if (up_left != null) {
            nodes.add(up_left);
        }
        if (up_right != null) {
            nodes.add(up_right);
        }
        if (down_left != null) {
            nodes.add(down_left);
        }
        if (down_right != null) {
            nodes.add(down_right);
        }
        return nodes;
    }
}
