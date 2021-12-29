package com.sadatmalik.aoc.daytwentythree.partone;

import java.util.*;

public class PartTwo extends AStarAmphipod {

    public PartTwo() {
        super();

        // define start and end game nodes
        endNode =  new PartTwoNode(
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {1, 10, 100, 1000},
                new int[] {1, 10, 100, 1000},
                new int[] {1, 10, 100, 1000},
                new int[] {1, 10, 100, 1000}
        );

        PartTwoNode testStart = new PartTwoNode(
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {10, 100, 10, 1000}, // B C B D
                new int[] {1000, 100, 10, 1}, // D C B A
                new int[] {1000, 10, 1, 100}, // D B A C
                new int[] {1, 1000, 100, 1} // A D C A
        );

        PartTwoNode puzzleStart = new PartTwoNode(
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {1000, 100, 1000, 10}, // D C D B
                new int[] {1000, 100, 10, 1}, // D C B A
                new int[] {1000, 10, 1, 100}, // D B A C
                new int[] {100, 1, 1, 10} // C A A B
        );

        //startNode = testStart; // 44169
        startNode = puzzleStart; // 41121
    }

    public GameNode process() {

        openNodes.add(startNode);
        int passes = 0;

        while(true) {
            PartTwoNode current = (PartTwoNode)openNodes.poll();
            if (passes++ % 1000 == 0) {
                //System.out.println("1000 passes...");
                printNode(current);
            }

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

    private void printScores(PartTwoNode parent, PartTwoNode node) {
        System.out.println("Node: ");
        printNode(node);
        System.out.println(node.finalCost());

        System.out.println("Parent:");
        printNode(parent);
        System.out.println(parent.finalCost());
    }

    private void printNode(PartTwoNode node) {
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
        System.out.print("###");
        for (int i = 0; i < node.getUpperB().length; i++) {
            System.out.print(node.amphipods.get(node.getUpperB()[i]) + "#");
        }
        System.out.println("##");
        System.out.print("###");
        for (int i = 0; i < node.getLowerA().length; i++) {
            System.out.print(node.amphipods.get(node.getLowerA()[i]) + "#");
        }

        System.out.println("##");
        System.out.print("  #");
        for (int i = 0; i < node.getLower().length; i++) {
            System.out.print(node.amphipods.get(node.getLower()[i]) + "#");
        }
        System.out.println("\n  #########\n");
    }

    private void printPath(PartTwoNode node) {
        List<PartTwoNode> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node);
            node = (PartTwoNode) parentByNode.get(node);
        }
        int move = 0;
        System.out.println("Path: ");
        for (PartTwoNode n : path) {
            System.out.println("Move #" + move++);
            printNode(n);
        }
    }

    public static void main(String[] args) {
        PartTwo aStar = new PartTwo();
        long start = System.currentTimeMillis();
        PartTwoNode lastNode = (PartTwoNode) aStar.process();
        long end = System.currentTimeMillis();
        aStar.printPath(lastNode);
        System.out.println("Final cost = " + lastNode.finalCost());
        System.out.println("Compute time = " + (end - start) + "s");
    }
}
