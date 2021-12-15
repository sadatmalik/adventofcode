package com.sadatmalik.aoc.dayfifteen.djikstra.priorityqueue;

import java.util.*;

// See:
// https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
public class DjikstraWithPriorityQueue {
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> queue;
    private int numNodes;
    List<List<Node> > adj;

    public DjikstraWithPriorityQueue(int numNodes) {
        this.numNodes = numNodes;
        dist = new int[numNodes];
        settled = new HashSet<>();
        queue = new PriorityQueue<>(numNodes, new Node());
    }

    // Dijkstra's Algorithm
    public void dijkstra(List<List<Node> > adj, int start) {
        this.adj = adj;
        for (int i = 0; i < numNodes; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        // Add source node to the priority queue, distance 0
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (settled.size() != numNodes) {
            if (queue.isEmpty()) {
                return;
            }
            // Removing minimum distance node from the priority queue
            int node = queue.remove().number;

            // Adding the node whose distance is finalized
            if (settled.contains(node)) {
                continue;
            }
            settled.add(node);
            executeNeighbors(node);
        }
    }

    // process all the neighbours of the passed node
    private void executeNeighbors(int node) {
        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of node
        for (int i = 0; i < adj.get(node).size(); i++) {
            Node neighbour = adj.get(node).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(neighbour.number)) {
                edgeDistance = neighbour.cost;
                newDistance = dist[node] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[neighbour.number])
                    dist[neighbour.number] = newDistance;

                // Add the current node to the queue
                queue.add(new Node(neighbour.number, dist[neighbour.number]));
            }
        }
    }

    public static void runAlgorithm(List<com.sadatmalik.aoc.dayfifteen.djikstra.Node> nodeList) {

        int numNodes = nodeList.size();
        int source = 0;

        List<List<Node>> adjacencyList = new ArrayList<>();

        // Initialize adjacency list for every node
        for (int i = 0; i < numNodes; i++) {
            List<Node> item = new ArrayList<>();
            adjacencyList.add(item);
        }

        // Inputs for the dpq graph
        for (com.sadatmalik.aoc.dayfifteen.djikstra.Node node : nodeList) {
            int nodeNumber = Integer.parseInt(node.getName());
            for (com.sadatmalik.aoc.dayfifteen.djikstra.Node adjacentNode : node.getAdjacentNodes().keySet()) {
                int adjNodeNumber = Integer.parseInt(adjacentNode.getName());
                int riskLevel = adjacentNode.getRiskLevel();
                adjacencyList.get(nodeNumber).add(new Node(adjNodeNumber, riskLevel));
            }
        }

        // Calculating the single source shortest path
        DjikstraWithPriorityQueue dpq = new DjikstraWithPriorityQueue(numNodes);
        dpq.dijkstra(adjacencyList, source);

        // Printing the shortest path to all the nodes
        // from the source node
        System.out.println("The shortest path from start to end node : " + dpq.dist[dpq.dist.length-1]);

//        for (int i = 0; i < dpq.dist.length; i++) {
//            System.out.println(source + " to " + i + " is "
//                    + dpq.dist[i]);
//        }
    }
}

// Class 2 - Node implementing comparator interface
class Node implements Comparator<Node> {
    public int number;
    public int cost;

    public Node() {}

    public Node(int node, int cost) {
        this.number = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        if (node1.cost < node2.cost) {
            return -1;
        }
        if (node1.cost > node2.cost) {
            return 1;
        }
        return 0;
    }
}