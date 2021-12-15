package com.sadatmalik.aoc.dayfifteen;

import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Node> neighbours;
    int nodeNumber;
    int riskLevel;
    boolean isStartNode;
    boolean isEndNode;

    public Node(int risklevel, int nodeNumber) {
        this.riskLevel = risklevel;
        this.nodeNumber = nodeNumber;
        this.neighbours = new ArrayList<>();
        this.isStartNode = false;
        this.isEndNode = false;
    }

    public void addNeighbour(Node node) {
        this.neighbours.add(node);
        //System.out.println(node.nodeNumber + " neighboured with " + this.nodeNumber);
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setStartNode(boolean isStartNode) {
        this.isStartNode = isStartNode;
    }

    public boolean isStartNode() {
        return isStartNode;
    }

    public void setEndNode(boolean isEndNode) {
        this.isEndNode = isEndNode;
    }

    public boolean isEndNode() {
        return isEndNode;
    }

    @Override
    public String toString() {
        return String.valueOf(nodeNumber + ": " + riskLevel);
    }
}
