package com.sadatmalik.aoc.dayeighteen;

public class Number {

    private Node root;
    private int depth;
    private int maxValue;
    private Node explodingNode;
    private Node splittingNode;

    private StringBuffer sb;

    public Number(String number) {
        root = new Node();
        int depth = 0;
        populate(root, number, depth);
    }

    private void populate(Node node, String number, int depth) {
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

    public Number add(Number number) {
        Node newRoot = new Node();

        newRoot.left = this.root;
        newRoot.left.parent = newRoot;

        newRoot.right = number.root;
        newRoot.right.parent = newRoot;

        resize(newRoot.left);
        resize(newRoot.right);
        
        this.root = newRoot;
        reduce();
        
        return this;
    }

    // exposed for testing
    public void reduce() {
        depth = 0;
        getDepth(root);
        if (depth > 4) {
            explode(explodingNode);
            explodingNode = null;
            //for testing
            //System.out.println(this);
            reduce();
            return;
        }

        maxValue = 0;
        getMaxValue(root);
        if (maxValue > 9) {
            split(splittingNode);
            splittingNode = null;
            //for testing
            //System.out.println(this);
            reduce();
        }
    }

    private void explode(Node node) {
        Node leftSibling = getLeftSibling(node);
        if (leftSibling != null) {
            leftSibling.value += node.left.value;
        }

        Node rightSibling = getRightSibling(node);
        if (rightSibling != null) {
            rightSibling.value += node.right.value;
        }

        node.left = null;
        node.right = null;
        node.value = 0;
    }

    private Node getLeftSibling(Node node) {
        if (node.parent == null) {
            return null;
        }
        if (!node.parent.left.equals(node)) {
            return getRightChild(node.parent.left);
        }
        return getLeftSibling(node.parent);
    }

    private Node getRightChild(Node node) {
        if (node.value != -1) {
            return node;
        }
        return getRightChild(node.right);
    }

    private Node getRightSibling(Node node) {
        if (node.parent == null) {
            return null;
        }
        if (!node.parent.right.equals(node)) {
            return getLeftChild(node.parent.right);
        }
        return getRightSibling(node.parent);
    }

    private Node getLeftChild(Node node) {
        if (node.value != -1) {
            return node;
        }
        return getLeftChild(node.left);
    }

    private void split(Node node) {
        int leftValue = (int) Math.floor(node.value / 2d);
        int rightValue = (int) Math.ceil(node.value / 2d);

        node.left = new Node(node.depth + 1);
        node.left.value = leftValue;
        node.left.parent = node;

        node.right = new Node(node.depth + 1);
        node.right.value = rightValue;
        node.right.parent = node;

        node.value = -1;
    }

    private void getDepth(Node node) {
        if (node ==  null) {
            return;
        }

        if (node.depth != -1) {
            if (node.depth > depth) {
                this.depth = node.depth;
                if (node.depth > 4 && explodingNode == null) {
                    explodingNode = node.parent;
                }
            }
        }
        getDepth(node.left);
        getDepth(node.right);
    }

    private void getMaxValue(Node node) {
        if (node == null) {
            return;
        }

        if (node.value != -1) {
            if (node.value > maxValue) {
                this.maxValue = node.value;
                if (node.value > 9 && splittingNode == null) {
                    splittingNode = node;
                }
            }
        }

        getMaxValue(node.left);
        getMaxValue(node.right);
    }

    private void resize(Node node) {
        if (node == null) {
            return;
        }

        node.depth++;

        resize(node.left);
        resize(node.right);
    }

     public String toString() {
        sb = new StringBuffer();
        print(root);
        sb.append(" (" + magnitude(root)+ ")");
        return sb.toString();
    }

    //exposed for testing
    int magnitude(Node node) {
        if (node.value != -1) {
            return node.value;
        }
        return ((3 * magnitude(node.left)) + (2 * magnitude(node.right)));
    }

    private void print(Node node) {
        if (node.left != null) {
            sb.append("[");
            print(node.left);
        }

        if (node.value == -1) {
            sb.append(",");
        } else {
            sb.append(node.value);
        }

        if (node.right != null) {
            print(node.right);
            sb.append("]");
        }
    }
}
