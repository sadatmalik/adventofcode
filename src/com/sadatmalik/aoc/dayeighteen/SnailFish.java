package com.sadatmalik.aoc.dayeighteen;

public class SnailFish {

    static Node root;

    public static void main(String[] args) {
        root = new Node();
        // String number = "[1,2]";
        // String number = "[[1,2],3]";
        // String number = "[9,[8,7]]";
        // String number = "[[1,9],[8,5]]";
        // String number = "[[[[1,2],[3,4]],[[5,6],[7,8]]],9]";
        // String number = "[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]";
        String number = "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]";
        populate(root, number, 0);
    }

    // call first time with root node
    static void populate(Node node, String number, int depth) {

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
