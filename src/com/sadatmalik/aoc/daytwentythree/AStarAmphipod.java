package com.sadatmalik.aoc.daytwentythree;

import java.util.*;

public class AStarAmphipod {

    Queue<GameNode> openNodes;
    Set<GameNode> closedNodes; // game nodes with no possible next move

    Set<GameNode> allNodes;
    Map<GameNode, GameNode> parentByNode; // maps nodes to their current set 'least cost' parent

    GameNode startNode;
    GameNode endNode;

    public AStarAmphipod() {
        this.openNodes = new PriorityQueue<GameNode>((GameNode g1, GameNode g2) -> {
            return g1.finalCost() < g2.finalCost() ? -1 :
                    g1.finalCost() > g2.finalCost() ? 1 : 0;
        });

        // todo - define start and end game nodes
    }

    public GameNode process() {
        while(true) {
            GameNode current = openNodes.poll();

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
                    }

                } else  {
                    parentByNode.put(n, current);
                    openNodes.add(n);
                }
            }
        }
        return null; // no path found
    }

}
