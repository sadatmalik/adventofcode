package com.sadatmalik.aoc.daytwentythree.astar;

// Define a game cell
public class Cell {

    // coordinates
    public int i, j;

    // parent game cell for path
    public Cell parent;

    // heuristic cost of the current cell
    public int heuristicCost;

    // final cost
    public int finalCost; // G + H with
    // G(n) the cost of the path from the start node to n
    // H(n) the heuristic that estimates the cheapest from n to the goal

    public boolean solution; // if cell is part of the solution path


    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "[" + i + "," + j +"]";
    }
}
