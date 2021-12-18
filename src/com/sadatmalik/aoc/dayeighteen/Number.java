package com.sadatmalik.aoc.dayeighteen;

public class Number {

    private Node root;
    private int maxDepth;

    Number(String number) {
        root = new Node();
        int depth = 0;
        populate(root, number, depth);
    }

    void populate(Node node, String number, int depth) {

        if (number.length() == 1) { // ignore last "]"
            return;
        }

        char c = number.charAt(0);
        String remainder = number.substring(1);

        if (c == '[') {  // go left
            depth++;
            Node left = new Node(depth);
            node.left = left;
            left.parent = node;
            populate(left, remainder, depth);
        }

        if (Character.isDigit(c)) { // populate and go up
            node.value = Character.getNumericValue(c);
            depth--;
            populate(node.parent, remainder, depth);
        }

        if (c == ',') { // go right
            depth++;
            Node right = new Node(depth);
            node.right = right;
            right.parent = node;
            populate(right, remainder, depth);
        }

        if (c == ']') { // go up
            depth--;
            populate(node.parent, remainder, depth);
        }
    }
}
