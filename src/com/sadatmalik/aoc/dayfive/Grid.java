package com.sadatmalik.aoc.dayfive;

public class Grid {

    static int maxGridX = 0;
    static int maxGridY = 0;

    int[][] grid;

    Grid() {
        grid = new int[++maxGridX][++maxGridY];
        for (int i = 0; i < maxGridX; i++) {
            for (int j = 0; j < maxGridY; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void add(Line line) {
        if (line.isHorizontal()) {
            addHorizontalLine(line);
        } else if (line.isVertical()) {
            addVerticalLine(line);
        } else {
            addDiagonalLine(line);
        }
    }

    private void addDiagonalLine(Line line) {
        int startX = line.getStart().getX();
        int endX = line.getEnd().getX();

        int startY = line.getStart().getY();
        int endY = line.getEnd().getY();

        if (startX > endX) { // R -> L
            if (startY < endY) { // top-down
                int y = startY;
                for (int i = startX; i >= endX; i--) {
                    grid[y][i]++;
                    y++;
                }
            } else if (startY > endY) { //bottom-up
                int y = startY;
                for (int i = startX; i >= endX; i--) {
                    grid[y][i]++;
                    y--;
                }
            }
        } else if (startX < endX) { // L -> R
            if (startY < endY) { //top-down
                int y = startY;
                for (int i = startX; i <= endX; i++) {
                    grid[y][i]++;
                    y++;
                }
            } else if (startY > endY) { //bottom-up
                int y = startY;
                for (int i = startX; i <= endX; i++) {
                    grid[y][i]++;
                    y--;
                }
            }
        }
    }

    private void addHorizontalLine(Line line) {
        int start = line.getStart().getX();
        int end = line.getEnd().getX();

        int y = line.getStart().getY();

        if (start < end) {
            for (int i = start; i <= end; i++) {
                grid[y][i]++;
            }
        } else {
            for (int i = start; i >= end; i--) {
                grid[y][i]++;
            }
        }
    }

    private void addVerticalLine(Line line) {
        int start = line.getStart().getY();
        int end = line.getEnd().getY();

        int x = line.getStart().getX();

        if (start < end) {
            for (int i = start; i <= end; i++) {
                grid[i][x]++;
            }
        } else {
            for (int i = start; i >= end; i--) {
                grid[i][x]++;
            }
        }
    }

    public int getNumOverlapPoints() {
        int points = 0;
        for (int i = 0; i < maxGridX; i++) {
            for (int j = 0; j < maxGridY; j++) {
                if (grid[i][j] > 1) {
                    points++;
                }
            }
        }
        return points;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < maxGridX; i++) {
            sb.append("\n[ ");
            for (int j = 0; j < maxGridY; j++) {
                sb.append(grid[i][j]);
                if (j < maxGridY-1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");
            if (i == maxGridX-1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
