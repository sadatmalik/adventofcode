package com.sadatmalik.aoc.daynine;

import java.util.HashSet;
import java.util.Set;

public class BasinSize {
    Node centre;
    Set<Node> nodesInBasin;

    public BasinSize(Node node) {
        this.centre = node;
        this.nodesInBasin = new HashSet<>();
        populateBasin(node);
    }

    public int getBasinSize() {
        return nodesInBasin.size();
    }

    private void populateBasin(Node node) {
        if (node == null || node.height == 9) {
            return;
        }
        if (nodesInBasin.contains(node)) {
            return;
        }

        nodesInBasin.add(node);

        populateBasin(node.left);
        populateBasin(node.right);
        populateBasin(node.up);
        populateBasin(node.down);
    }

}
