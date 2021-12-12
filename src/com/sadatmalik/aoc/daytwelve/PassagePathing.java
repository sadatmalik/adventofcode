package com.sadatmalik.aoc.daytwelve;

import com.sadatmalik.aoc.FileReader;

import java.util.*;

public class PassagePathing {

    static Node root;
    static List<Node> visitedSmallCaves;
    static Map<String, Node> nodeMap;
    static int pathsFound;

    static Set<String> paths;

    public static void main(String[] args) {
        // 1. with test data
        runPathFinder("data/daytwelve/testdata1.txt");
        runPathFinder("data/daytwelve/testdata2.txt");
        runPathFinder("data/daytwelve/testdata3.txt");

        // 1. with puzzle data
        runPathFinder("data/daytwelve/puzzledata1.txt");

        //2. with test data
        runPathFinderWithExemption("data/daytwelve/testdata1.txt");
        runPathFinderWithExemption("data/daytwelve/testdata2.txt");
        runPathFinderWithExemption("data/daytwelve/testdata3.txt");

        //2. with puzzle data
        runPathFinderWithExemption("data/daytwelve/puzzledata1.txt");
    }

    private static void runPathFinder(String filename) {
        root = null;
        visitedSmallCaves = new ArrayList<>();
        nodeMap = new HashMap<>();
        pathsFound = 0;

        List<String[]> dataPoints = getNodes(filename);
        printDataPoints(dataPoints);
        setUpNodeGraph(dataPoints);
        getPaths();
    }

    private static void runPathFinderWithExemption(String filename) {
        root = null;
        visitedSmallCaves = new ArrayList<>();
        nodeMap = new HashMap<>();
        pathsFound = 0;

        List<String[]> dataPoints = getNodes(filename);
        printDataPoints(dataPoints);
        setUpNodeGraph(dataPoints);
        getPathsWithExemption();
    }

    private static List<String[]> getNodes(String filename) {
        ArrayList<String> nodeData = FileReader.getDataFromFile(filename);
        List<String[]> nodePoints = new ArrayList<>();

        for (String line : nodeData) {
            String[] startAndEnd = line.split("-");
            nodePoints.add(startAndEnd);
        }
        return nodePoints;
    }

    private static void setUpNodeGraph(List<String[]> dataPoints) {
        nodeMap = new HashMap<>();

        for (String[] point : dataPoints) {
            String start = point[0];
            String end = point[1];

            boolean possibleRoot = false;
            if (start.equals("start") || end.equals("start")) {
                possibleRoot = true;
            }

            if(!nodeMap.containsKey(start) && !nodeMap.containsKey(end)) {
                Node node = new Node(start);
                Node nextNode = new Node(end);
                node.addNext(nextNode);

                if (root == null && possibleRoot) {
                    setRoot(node, nextNode);
                }
                nodeMap.put(start, node);
                nodeMap.put(end, nextNode);
            }
            else if (!nodeMap.containsKey(start) && nodeMap.containsKey(end)) {
                Node node = new Node(start);
                Node nextNode = nodeMap.get(end);
                node.addNext(nextNode);

                if (root == null && possibleRoot) {
                    setRoot(node, nextNode);
                }

                nodeMap.put(start, node);
            }
            else if (nodeMap.containsKey(start) && !nodeMap.containsKey(end)) {
                Node node = nodeMap.get(start);
                Node nextNode = new Node(end);
                node.addNext(nextNode);

                if (root == null && possibleRoot) {
                    setRoot(node, nextNode);
                }

                nodeMap.put(end, nextNode);
            }
            else { // node map contains start and end
                Node node = nodeMap.get(start);
                Node nextNode = nodeMap.get(end);

                if (root == null && possibleRoot) {
                    setRoot(node, nextNode);
                }

                node.addNext(nextNode);
            }
        }

        System.out.print("Caves: ");
        printNodeMap(nodeMap);
    }

    private static void setRoot(Node node, Node nextNode) {
        if (node.nodeName.equals("start")) {
            root = node;
            return;
        }
        if (nextNode.nodeName.equals("start")) {
            root = nextNode;
        }
    }

    private static void getPaths() {
        visitedSmallCaves = new ArrayList<>();
        StringBuffer sb = new StringBuffer("start");

        System.out.println("\nPaths:");
        visitCaves(root, sb, visitedSmallCaves);
        System.out.println("\nTotal paths = " + pathsFound);
    }

    private static void getPathsWithExemption() {

        System.out.println("\nPaths:");
        ArrayList<Node> smallCaves = new ArrayList<>();
        for (Node node : nodeMap.values()) {
            if (node.isSmallCave) {
                if (!node.nodeName.equals("start") && !node.nodeName.equals("end")) {
                    smallCaves.add(node);
                }
            }
        }

        paths = new HashSet<>();

        for (Node node : smallCaves) {
            visitedSmallCaves = new ArrayList<>();
            StringBuffer sb = new StringBuffer("start");
            System.out.println("Double visit node = " + node);
            visitCavesWithExemption(root, sb, visitedSmallCaves, node, true);
            System.out.println("\nTotal paths = " + pathsFound + "\n");
        }


    }

    private static void visitCaves(Node node, StringBuffer sb, List visitedSmallCaves) {

        if (node.nodeName.equals("end")) {
            sb.append(", " + node.nodeName);
            System.out.println(sb);
            pathsFound++;
            return;
        }

        if (node.isSmallCave && visitedSmallCaves.contains(node)) {
            // System.out.println("end of path");
            return;
        }

        // visit cave
        if (node != root) {
            sb.append(", " + node.nodeName);
        }
        if (node.isSmallCave) {
//            System.out.println("Adding to visited = " + node);
            visitedSmallCaves.add(node);
        }

        for (Node nextNode : node.nextNodeList) {
            ArrayList<Node> visited = new ArrayList(visitedSmallCaves);

//            System.out.println("\nCurrent Path = " + sb);
//            System.out.println("Next = " + nextNode);
//            System.out.println("Visited = " + visited);

            visitCaves(nextNode, new StringBuffer(sb), visited);
        }

    }

    private static void visitCavesWithExemption(Node node, StringBuffer sb, List visitedSmallCaves,
                                                Node exemption, boolean isExempt) {

        if (node.nodeName.equals("end")) {
            sb.append(", " + node.nodeName);
            String path = sb.toString();
            if (!paths.contains(path)) {
                System.out.println(sb);
                paths.add(path);
                pathsFound++;
            }
            return;
        }

        if (node.isSmallCave && visitedSmallCaves.contains(node)) {
            return;
        }

        // visit cave
        if (node != root) {
            sb.append(", " + node.nodeName);
        }
        if (node.isSmallCave) {
            if (node.equals(exemption) && isExempt) {
                isExempt = false;
            } else {
                visitedSmallCaves.add(node);
            }
        }

        for (Node nextNode : node.nextNodeList) {
            ArrayList<Node> visited = new ArrayList(visitedSmallCaves);
            visitCavesWithExemption(nextNode, new StringBuffer(sb), visited, exemption, isExempt);
        }

    }

    private static void printDataPoints(List<String[]> dataPoints) {
        System.out.print("[");
        for (int i = 0; i < dataPoints.size(); i++) {
            String[] item = dataPoints.get(i);
            System.out.print(Arrays.toString(item));
            if (i < dataPoints.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private static void printNodeMap(Map<String, Node> nodeMap) {
        System.out.println();
        for (Node node : nodeMap.values()) {
            System.out.println("[" + node + ": " + node.nextNodeList + "]");
        }
    }


}
