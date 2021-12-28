package com.sadatmalik.aoc.daytwentythree;

import java.util.List;

public class GameNode {

    private int[] hallway;
    private int[] upper;
    private int[] lower;

    private int fCost;
    private int gCost;
    private int hCost;

    public GameNode() {
        int[] hallway = new int[11]; //intialises to 0
        int[] upper = new int[4]; // initialize to puzzle inputs
        int[] lower = new int[4];
    }

    public GameNode(int[] hallway, int[] upper, int[] lower) {
        this.hallway = hallway;
        this.upper = upper;
        this.lower = lower;
    }

    public int finalCost() {
        return fCost;
    }

    public List<GameNode> getAllPossibleMoves() {
        // todo -- provide an implementation

        return null;
    }
}
