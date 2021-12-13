package com.sadatmalik.aoc.daythirteen;

import java.util.ArrayList;

public class Grid {

    char[][] grid;
    int gridX;
    int gridY;

    private Grid(int x, int y) {
        this.grid = new char[x][y];
        gridX = x;
        gridY = y;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                grid[j][i] = '.';
            }
        }
    }

    public Grid foldY(int y) {
        Grid foldedGrid = new Grid(gridX, y);

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < gridX; j++) {
                foldedGrid.grid[j][i] = this.grid[j][i];
            }
        }

        for (int i = y+1; i < gridY; i++) {
            for (int j = 0; j < gridX; j++) {
                if (isMarked(j, i)) {
                    foldedGrid.grid[j][y-(i-y)] = '#';
                }
            }
        }

        return foldedGrid;
    }

    public Grid foldX(int x) {
        Grid foldedGrid = new Grid(x, gridY);

        for (int i = 0; i < gridY; i++) {
            for (int j = 0; j < x; j++) {
                foldedGrid.grid[j][i] = this.grid[j][i];
            }
        }

        for (int i = 0; i < gridY; i++) {
            for (int j = x+1; j < gridX; j++) {
                if (isMarked(j, i)) {
                    foldedGrid.grid[x-(j-x)][i] = '#';
                }
            }
        }

        return foldedGrid;
    }

    public static Grid populateGrid(ArrayList<String> dataPoints) {

        int x = 0;
        int y = 0;

        for (String dataPoint : dataPoints) {
            String[] coordinates = dataPoint.split(",");
            int dpX = Integer.parseInt(coordinates[0]);
            int dpY = Integer.parseInt(coordinates[1]);
            if (dpX > x) {
                x = dpX;
            }
            if (dpY > y) {
                y = dpY;
            }
        }

        Grid grid = new Grid(x+1, y+1);

        for (String dataPoint : dataPoints) {
            String[] coordinates = dataPoint.split(",");
            grid.mark(Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1]));
        }

        return grid;
    }


    public boolean isMarked(int x, int y) {
        return grid[x][y] == '#';
    }

    public void mark(int x, int y) {
        grid[x][y] = '#';
    }

    public int getNumVisibleDots() {
        int dots = 0;
        for (int i = 0; i < gridY; i++) {
            for (int j = 0; j < gridX; j++) {
                if (isMarked(j,i)) {
                    dots++;
                }
            }
        }
        return dots;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Grid: \n");

        for (int i = 0; i < gridY; i++) {
            for (int j = 0; j < gridX; j++) {
                sb.append(grid[j][i] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
