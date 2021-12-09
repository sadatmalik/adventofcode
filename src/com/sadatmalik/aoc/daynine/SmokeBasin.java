package com.sadatmalik.aoc.daynine;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmokeBasin {
    static Node[][] nodes;
    static List<Node> nodeList; //easy scanning
    static int basinX;
    static int basinY;

    public static void main(String[] args) {
        //1. with test data
        runSimulation("data/daynine/testdata.txt");

        //1. with puzzle data
        runSimulation("data/daynine/puzzledata1.txt");

    }

    private static void runSimulation(String filename) {
        nodeList = new ArrayList<>();

        // initialize node grid to correct x and y dimensions for test data
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        initializeGrid(data);
        scanBasinFloorData(data);
        printNodeGrid();
        System.out.println("\nTotal risk level = " +processRiskLevel() + "\n");
    }

    private static void scanBasinFloorData(ArrayList<String> data) {
        // read first line
        for (int row = 0; row < data.size(); row++) {
            String[] dataPoints = data.get(row).split("");
            System.out.println(Arrays.toString(dataPoints));

            // set nodes
            Node node = null;

            // first row - no up
            if (row == 0) {
                for (int x = 0; x < dataPoints.length; x++) {
                    // first column - no left
                    if (x == 0) {
                        node = new Node(Integer.parseInt(dataPoints[x]));
                        addNode(node, x, row);
                    } else {
                        node = new Node(Integer.parseInt(dataPoints[x]));
                        setLeftNode(node, x, row);
                        addNode(node, x, row);
                    }
                }
            } else {

                // all other rows have an up
                for (int x = 0; x < dataPoints.length; x++) {
                    // first column - no left - but has up
                    if (x == 0) {
                        node = new Node(Integer.parseInt(dataPoints[x]));
                        setTopNode(node, x, row);
                        addNode(node, x, row);
                    } else {
                        // all others have left and up
                        node = new Node(Integer.parseInt(dataPoints[x]));
                        setLeftNode(node, x, row);
                        setTopNode(node, x, row);
                        addNode(node, x, row);
                    }
                }

            }
        }
    }

    private static void addNode(Node node, int x, int y) {
        nodes[x][y] = node;
        nodeList.add(node);
    }

    private static void setLeftNode(Node node, int x, int y) {
        Node leftNode = nodes[x-1][y];
        node.left = leftNode;
        leftNode.right = node;
    }

    private static void setTopNode(Node node, int x, int y) {
        Node upNode = nodes[x][y-1];
        node.up = upNode;
        upNode.down = node;
    }

    private static int processRiskLevel() {
        int riskLevel = 0;
        for (Node node : nodeList) {
            if (node.isLow()) {
                System.out.println("Found low point = " + node.height);
                riskLevel += node.riskLevel;
            }
        }
        return riskLevel;
    }


    private static void initializeGrid(ArrayList<String> data) {
        basinY = data.size(); // rows
        String firstRow = data.get(0);
        basinX = firstRow.length();
        System.out.println("x = " + basinX + ", y = " + basinY);

        nodes = new Node[basinX][basinY];
    }

    private static void printNodeGrid() {
        System.out.println("\nNode Grid:");
        for (int y = 0; y < basinY; y++) {
            System.out.print("[ ");
            for (int x = 0; x < basinX; x++) {
                System.out.print(nodes[x][y].height);
                if (x < basinX-1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" ]");
        }
    }
}
