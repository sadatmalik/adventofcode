package com.sadatmalik.aoc.dayfifteen.djikstra;

import com.sadatmalik.aoc.FileReader;
import com.sadatmalik.aoc.dayfifteen.djikstra.priorityqueue.DjikstraWithPriorityQueue;

import java.util.ArrayList;
import java.util.List;

public class ChitonPath {

    static int gridX;
    static int gridY;
    static Node[][] grid;
    static List<Node> nodes;
    //static Map<Node, Integer> riskLevel;
    static Node start;
    static Node end;

    public static void main(String[] args) {
        // 1. with test data
        //runPathfinder("data/dayfifteen/testdata.txt");

        // 1. with puzzle data
        //runPathfinder("data/dayfifteen/puzzledata1.txt");

        // 2. with test data
        //runPathfinder2("data/dayfifteen/testdata.txt");

        // 2. with test data
        runPathfinder2("data/dayfifteen/puzzledata1.txt");

    }

    private static void runPathfinder(String filename) {
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        initializeNodeGrid(data);
        setupNodePaths();
        DjikstraWithPriorityQueue.runAlgorithm(nodes);
        //findShortestPath();
    }

    private static void runPathfinder2(String filename) {
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        initializeNodeGrid2(data);
        setupNodePaths();
        DjikstraWithPriorityQueue.runAlgorithm(nodes);
        //findShortestPath();
    }

    private static void initializeNodeGrid(ArrayList<String> data) {
        gridX = data.size(); // rows
        String firstRow = data.get(0);
        gridY = firstRow.length();
        System.out.println("x = " + gridX + ", y = " + gridY);

        grid = new Node[gridX][gridY];
        nodes = new ArrayList<>();


        int nodeNumber = 1;
        for (int y = 0; y < gridY; y++) {
            String row = data.get(y);
            String[] values = row.split("");
            for (int x = 0; x < gridX; x++) {
                Node node = new Node(String.valueOf(nodeNumber), Integer.parseInt(values[x]));
                grid[x][y] = node;
                nodes.add(node);
            }
        }
    }

    private static void initializeNodeGrid2(ArrayList<String> data) {
        gridX = data.size(); // rows
        String firstRow = data.get(0);
        gridY = firstRow.length();
        System.out.println("x = " + gridX + ", y = " + gridY);

        grid = new Node[gridX][gridY];
        nodes = new ArrayList<>();

        int nodeNumber = 1;
        for (int y = 0; y < gridY; y++) {
            String row = data.get(y);
            String[] values = row.split("");
            for (int x = 0; x < gridX; x++) {
                Node node = new Node(String.valueOf(nodeNumber), Integer.parseInt(values[x]));
                grid[x][y] = node;
            }
        }

        // second pass
        gridY *= 5;
        gridX *= 5;
        grid = TranslateGrid.translate(grid, gridX, gridY);
        System.out.println("New x = " + gridX + ", New y = " + gridY);
        for (int y = 0; y < gridY; y++) {
            for (int x = 0; x < gridX; x++) {
                Node node = grid[x][y];
                nodes.add(node);
            }
        }
        //printGrid();
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
        node.addDestination(leftNode, leftNode.getRiskLevel());
        leftNode.addDestination(node, node.getRiskLevel());
    }

    private static void setTopNode(Node node, int x, int y) {
        Node upNode = grid[x][y-1];
        node.addDestination(upNode, upNode.getRiskLevel());
        upNode.addDestination(node, node.getRiskLevel());
    }

    private static void findShortestPath() {
        Graph graph = new Graph();
        for (Node node : nodes) {
            graph.addNode(node);
        }
        graph = Djikstra.calculateShortestPathFromSource(graph, start);

        System.out.println("Shortest path length = " + end.getDistance());
    }

    private static void printGrid() {
        for (int y = 0; y < gridY; y++) {
            for (int x = 0; x < gridX; x++) {
                System.out.print(grid[x][y]);
            }
            System.out.println();
        }
    }



}
