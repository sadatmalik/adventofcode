package com.sadatmalik.aoc.dayfifteen.initial;

import com.sadatmalik.aoc.FileReader;

import java.util.*;

public class Chiton {

    static int gridX;
    static int gridY;
    static Node[][] grid;
    static List<Node> nodes;
    static Node start;
    static Node end;

    static Map<Node, Map<Node, Integer>> graph;
    static Map<Node, Integer> costsFromStart;
    static Map<Node, Node> parents;

    static List<Node> visited;

    public static void main(String[] args) {
        // 1. with test data
        //runPathfinder("data/dayfifteen/testdata.txt");

        // 1. with puzzle data
        runPathfinder("data/dayfifteen/puzzledata1.txt");

    }

    private static void runPathfinder(String filename) {
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        initializeGrid(data);
        printGrid();
        initializeNodes();
        initializeNodeGraph();
        //printGraph();
        findLeastRiskyPath();
        System.out.println("Least risk path = " + costsFromStart.get(end));
    }

    private static void initializeGrid(ArrayList<String> data) {
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
                grid[x][y] = new Node(Integer.parseInt(values[x]), nodeNumber++);
                nodes.add(grid[x][y]);
            }
        }
    }

    private static void initializeNodes() {
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
                        node.setStartNode(true);
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
                            node.setEndNode(true);
                            end = node;
                        }
                    }
                }

            }
        }
    }

    private static void setTopNode(Node node, int x, int y) {
        Node upNode = grid[x][y-1];
        node.addNeighbour(upNode);
        upNode.addNeighbour(node);
    }

    private static void setLeftNode(Node node, int x, int y) {
        Node leftNode = grid[x-1][y];
        node.addNeighbour(leftNode);
        leftNode.addNeighbour(node);
    }

    private static void initializeNodeGraph() {
        graph = new HashMap<>();
        costsFromStart = new HashMap<>();
        parents = new HashMap<>();

        Set<Node> processed = new HashSet<>();

        for (Node node : nodes) {
            List<Node> neighbours = node.getNeighbours();
            Map<Node, Integer> edgeCosts = new HashMap<>();
            for (Node neighbour : neighbours) {
                if (!processed.contains(neighbour)) {
                    edgeCosts.put(neighbour, neighbour.riskLevel);
                    if (node.isStartNode()) {
                        costsFromStart.put(neighbour, neighbour.riskLevel);
                    } else {
                        costsFromStart.put(neighbour, Integer.MAX_VALUE);
                    }
                }
                processed.add(node);
            }
            graph.put(node, edgeCosts);
        }
    }

    private static void findLeastRiskyPath() {
        visited = new ArrayList<>();
        Node node = findLowestCostNode();
        int count = 1;
        while (node != null) {
            //printGraph();
            System.out.println(count++);
            Integer cost = costsFromStart.get(node);
            Map<Node, Integer> neighbours = graph.get(node);

            for (Node neighbour : neighbours.keySet()) {
                Integer newCost = cost + neighbours.get(neighbour);
                if (costsFromStart.get(neighbour) > newCost) {
                    costsFromStart.put(neighbour, newCost);
                    parents.put(neighbour, node);
                }
            }
            visited.add(node);
            node = findLowestCostNode();
        }
        System.out.println("Ending while loop");
    }

    private static Node findLowestCostNode() {
        Integer lowestCost = Integer.MAX_VALUE;
        Node lowestCostNode = null;

        for (Node node : costsFromStart.keySet()) {
            Integer cost = costsFromStart.get(node);
            if (cost != null && cost < lowestCost && !visited.contains(node)) {
                lowestCost = cost;
                lowestCostNode = node;
            }
        }
        return lowestCostNode;
    }


    private static void printGrid() {
        for (int y = 0; y < gridY; y++) {
            System.out.print("[");
            for (int x = 0; x < gridX; x++) {
                System.out.print(grid[x][y]);
                if (x < gridX-1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    private static void printGraph() {
        StringBuffer sb = new StringBuffer();
        for (Node node : nodes) {
            Map<Node, Integer> neighboursAndCosts;
            sb.append("Node " + node + ", Neighbours =");
            Map<Node, Integer> neighbours = graph.get(node);
            for (Node entry : neighbours.keySet()) {
                Integer costFromStart = costsFromStart.get(entry);
                if (costFromStart < Integer.MAX_VALUE) {
                    sb.append(" [" + entry + ", Cost from start: " + costsFromStart.get(entry) + "]");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
