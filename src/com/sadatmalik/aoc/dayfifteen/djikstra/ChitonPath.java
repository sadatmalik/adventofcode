package com.sadatmalik.aoc.dayfifteen.djikstra;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChitonPath {

    static int gridX;
    static int gridY;
    static Node[][] grid;
    static List<Node> nodes;
    static Map<Node, Integer> riskLevel;
    static Node start;
    static Node end;

    public static void main(String[] args) {
        // 1. with test data
        //runPathfinder("data/dayfifteen/testdata.txt");
        // 1. with puzzle data
        //runPathfinder("data/dayfifteen/puzzledata1.txt");

        // 2. with test data
    }

    private static void runPathfinder(String filename) {
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        initializeNodeGrid(data);
        setupNodePaths();
        findShortestPath();
    }

    private static void initializeNodeGrid(ArrayList<String> data) {
        gridX = data.size(); // rows
        String firstRow = data.get(0);
        gridY = firstRow.length();
        System.out.println("x = " + gridX + ", y = " + gridY);

        grid = new Node[gridX][gridY];
        nodes = new ArrayList<>();
        riskLevel = new HashMap<>();

        int nodeNumber = 1;
        for (int y = 0; y < gridY; y++) {
            String row = data.get(y);
            String[] values = row.split("");
            for (int x = 0; x < gridX; x++) {
                Node node = new Node(String.valueOf(nodeNumber));
                grid[x][y] = node;
                nodes.add(node);


                // test
                riskLevel.put(node, Integer.parseInt(values[x]));
            }
        }
    }

    private static void setupNodePaths() {
        // read first line
        for (int y = 0; y < gridY; y++) {
            // set nodes
            Node node = null;
            // first row - no up
            if (y == 0) {
                for (int x = 0; x < gridX; x++) {
                    // first column - no left
                    if (x == 0) {
                        node = grid[x][y];
                        start = node;
                        //addNode(node, x, row);
                    } else {
                        node = grid[x][y];
                        setLeftNode(node, x, y);
                        // addNode(node, x, row);
                    }
                }
            } else {

                // all other rows have an up
                for (int x = 0; x < gridX; x++) {
                    // first column - no left - but has up
                    if (x == 0) {
                        node = grid[x][y];
                        setTopNode(node, x, y);
                        //addNode(node, x, row);
                    } else {
                        // all others have left and up
                        node = grid[x][y];
                        setLeftNode(node, x, y);
                        setTopNode(node, x, y);
                        //addNode(node, x, row);

                        if (x == gridX-1) {
                            end = node;
                        }
                    }
                }

            }
        }
    }

    private static void setLeftNode(Node node, int x, int y) {
        Node leftNode = grid[x-1][y];
        node.addDestination(leftNode, riskLevel.get(leftNode));
        leftNode.addDestination(node, riskLevel.get(node));
    }

    private static void setTopNode(Node node, int x, int y) {
        Node upNode = grid[x][y-1];
        node.addDestination(upNode, riskLevel.get(upNode));
        upNode.addDestination(node, riskLevel.get(node));
    }

    private static void findShortestPath() {
        Graph graph = new Graph();
        for (Node node : nodes) {
            graph.addNode(node);
        }
        graph = Djikstra.calculateShortestPathFromSource(graph, start);

        System.out.println("Shortest path length = " + end.getDistance());
    }


}
