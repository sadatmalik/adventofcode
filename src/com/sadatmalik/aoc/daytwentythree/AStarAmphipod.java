package com.sadatmalik.aoc.daytwentythree;

import java.util.*;

public class AStarAmphipod {

    Queue<GameNode> openNodes;
    Set<GameNode> closedNodes; // game nodes with no possible next move

    Map<GameNode, GameNode> parentByNode; // maps nodes to their current set 'least cost' parent

    GameNode startNode;
    GameNode endNode;

    public static void main(String[] args) {
        AStarAmphipod aStar = new AStarAmphipod();
        GameNode lastNode = aStar.process();
        aStar.printPath(lastNode);
        System.out.println("Final cost = " + lastNode.finalCost());
    }

    public AStarAmphipod() {
        this.openNodes = new PriorityQueue<GameNode>((GameNode g1, GameNode g2) -> {
            return g1.finalCost() < g2.finalCost() ? -1 :
                    g1.finalCost() > g2.finalCost() ? 1 : 0;
        });

        closedNodes = new HashSet<>();
        parentByNode = new HashMap<>();

        // define start and end game nodes
        endNode =  new GameNode(
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {1, 10, 100, 1000},
                new int[] {1, 10, 100, 1000}
        );

        GameNode testStart = new GameNode(
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {10, 100, 10, 1000}, // B C B D
                new int[] {1, 1000, 100, 1} // A D C A
        );

        GameNode puzzleStart = new GameNode(
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {1000, 100, 1000, 10}, // D C D B
                new int[] {100, 1, 1, 10} // C A A B
        );

        startNode = puzzleStart;
    }

    public GameNode process() {

        openNodes.add(startNode);

        while(true) {
            GameNode current = openNodes.poll();
            //printNode(current);

            if (current.equals(endNode)) // reached goal
                return current;

            if (current == null) // out of game options
                break;

            if (closedNodes.contains(current)) // skip locked game nodes
                continue;

            List<GameNode> nextNodes = current.getAllPossibleMoves();

            if (nextNodes == null || nextNodes.isEmpty()) { // add to closed if no next moves possible
                closedNodes.add(current);
                continue;
            }

            for (GameNode n : nextNodes) {

                if (closedNodes.contains(n)) // skip locked game next nodes
                    continue;

                if (parentByNode.containsKey(n)) {
                    Optional<GameNode> storedNode = parentByNode.keySet().stream()
                            .filter((gameNode -> gameNode.equals(n)))
                            .findFirst();

                    GameNode pastNode = storedNode.get();
                    // todo -- may be easier or may need to keep these in a finalCostByNode map
                    if (n.finalCost() < pastNode.finalCost()) {
                        parentByNode.put(n, current); // update cost and parent with n and current
                        openNodes.add(n);
                        //printScores(n, current);
                    }

                } else  {
                    parentByNode.put(n, current);
                    openNodes.add(n);
                    //printScores(n, current);
                }
            }
        }
        return null; // no path found
    }

    private void printScores(GameNode parent, GameNode node) {
        System.out.println("Node: ");
        printNode(node);
        System.out.println(node.finalCost());

        System.out.println("Parent:");
        printNode(parent);
        System.out.println(parent.finalCost());
    }

    private void printNode(GameNode node) {
        System.out.println("#############");
        System.out.print("#");
        for (int i = 0; i < node.getHallway().length; i++) {
            System.out.print(node.amphipods.get(node.getHallway()[i]));
        }
        System.out.println("#");
        System.out.print("###");
        for (int i = 0; i < node.getUpper().length; i++) {
            System.out.print(node.amphipods.get(node.getUpper()[i]) + "#");
        }
        System.out.println("##");
        System.out.print("  #");
        for (int i = 0; i < node.getLower().length; i++) {
            System.out.print(node.amphipods.get(node.getLower()[i]) + "#");
        }
        System.out.println("\n  #########\n");
    }

    private void printPath(GameNode node) {
        List<GameNode> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node);
            node = parentByNode.get(node);
        }
        int move = 0;
        for (GameNode n : path) {
            System.out.println("Move #" + move++);
            printNode(n);
        }
    }
}
