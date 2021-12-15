package com.sadatmalik.aoc.dayfifteen.djikstra;

public class FindPath {
    public static void main(String[] args) {
        Node nodeA = new Node("A", 0);
        Node nodeB = new Node("B", 10);
        Node nodeC = new Node("C", 15);
        Node nodeD = new Node("D", 12);
        Node nodeE = new Node("E", 10);
        Node nodeF = new Node("F", 15);

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = Djikstra.calculateShortestPathFromSource(graph, nodeA);

        System.out.println(nodeE.getDistance());
    }
}
