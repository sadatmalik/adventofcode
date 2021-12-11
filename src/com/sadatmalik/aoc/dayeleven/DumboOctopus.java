package com.sadatmalik.aoc.dayeleven;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DumboOctopus {

    static Node[][] nodes;
    static List<Node> nodeList; // easy scanning

    static int gridX;
    static int gridY;

    public static void main(String[] args) {
        //part 1 + 2. with test data
        //runSimulation("data/dayeleven/testdata.txt");

        //part 1 + 2. with puzzle data
        runSimulation("data/dayeleven/puzzledata1.txt");
    }

    private static void runSimulation(String filename) {
        nodeList = new ArrayList<>();

        // initialize node grid to correct x and y dimensions for test data
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        initializeGrid(data);
        scanOctopusGrid(data);
        printNodes();
        simulateFlashes(400);
    }

    private static void initializeGrid(ArrayList<String> data) {
        gridX = data.size(); // rows
        String firstRow = data.get(0);
        gridY = firstRow.length();
        System.out.println("x = " + gridX + ", y = " + gridY);

        nodes = new Node[gridX][gridY];
    }

    private static void scanOctopusGrid(ArrayList<String> data) {
        // read first line
        for (int row = 0; row < data.size(); row++) {
            String[] dataPoints = data.get(row).split("");
            //System.out.println(Arrays.toString(dataPoints));

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

                        //set diagonals
                        setDiagonalNodes(node, x, row);

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

    private static void setDiagonalNodes(Node node, int x, int row) {
        // up-left and bottom-right
        Node upLeftNode = nodes[x-1][row-1];
        node.up_left = upLeftNode;
        upLeftNode.down_right = node;

        // up-right and bottom-left
        Node leftNode = nodes[x-1][row];
        Node upNode = nodes[x][row-1];
        leftNode.up_right = upNode;
        upNode.down_left = leftNode;
    }

    private static void setTopNode(Node node, int x, int y) {
        Node upNode = nodes[x][y-1];
        node.up = upNode;
        upNode.down = node;
    }

    private static void simulateFlashes(int numSteps) {

        int step = 1;
        int totalFlashes = 0;
        while(step <= numSteps) {

            // increase all energy levels by 1
            for (Node node : nodeList) {
                node.energyLevel++;
            }

            // get all nodes that are ready to flash
            ArrayList<Node> readyToFlash = new ArrayList<>();
            for (Node node : nodeList) {
                if (node.energyLevel > 9) {
                    readyToFlash.add(node);
                }
            }

            // flash nodes that haven't already flashed
            ArrayList<Node> flashedDuringThisStep = new ArrayList<>();
            while (readyToFlash.size() > 0) {
                for (Node node : readyToFlash) {
                    if (!flashedDuringThisStep.contains(node)) {
                        node.flash();
                        flashedDuringThisStep.add(node);
                    }
                }

                // next round
                readyToFlash = new ArrayList<>();
                for (Node node : nodeList) {
                    if (!flashedDuringThisStep.contains(node) && node.energyLevel > 9) {
                        readyToFlash.add(node);
                    }
                }
            }

            // reset all the flashed
            for (Node node : flashedDuringThisStep) {
                node.energyLevel = 0;
            }

            totalFlashes += flashedDuringThisStep.size();
            if (step % 10 == 0) {
                System.out.println("After step " + step + ", total flashes = " + totalFlashes +
                        " (flashed this step = " + flashedDuringThisStep.size() + ")");
                printNodes();
            }
            if (flashedDuringThisStep.size() == 100) {
                System.out.println("All flashed at step = " + step);
                return;
            }
            step++;
        }
    }

    private static void printNodes() {
        for (int y = 0; y < nodes.length; y++) {
            System.out.print("[");
            for (int x = 0; x < nodes[y].length; x++) {
                System.out.print(nodes[x][y].energyLevel);
                if (x != nodes[y].length-1) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
        System.out.println();
    }

}
