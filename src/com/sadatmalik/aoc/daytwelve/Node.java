package com.sadatmalik.aoc.daytwelve;

import java.util.ArrayList;
import java.util.List;

public class Node {

    List<Node> nextNodeList;
    String nodeName;
    boolean isSmallCave;

    public Node(String nodeName) {
        this.nodeName = nodeName;
        if (isLowerCase(nodeName)) {
            isSmallCave = true;
        } else {
            isSmallCave = false;
        }
        nextNodeList = new ArrayList<>();
    }

    private boolean isLowerCase(String nodeName) {
        char[] chars = nodeName.toCharArray();
        boolean isLower = true;
        for (char c : chars) {
            if (!Character.isLowerCase(c)) {
                isLower = false;
                break;
            }
        }
        return isLower;
    }

    public void addNext(Node node) {
        // add forward and reverse path
        this.nextNodeList.add(node);
        node.nextNodeList.add(this);
    }

    @Override
    public String toString() {
        return nodeName;
    }
}
