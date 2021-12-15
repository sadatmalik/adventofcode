package com.sadatmalik.aoc.dayfifteen.djikstra;

public class TranslateGrid {

    static int oldGridLength;
    static Node[][] newGrid;

    public static Node[][] translate(Node[][] grid, int gridX, int gridY) {

        newGrid = new Node[gridX][gridY];
        oldGridLength = grid.length;

        // copy first square
        for (int y = 0; y < oldGridLength; y++) {
            for (int x = 0; x < oldGridLength; x++) {
                int newNodeNum = (y * gridY) + x;
                int newRiskLevel = grid[x][y].getRiskLevel();
                newGrid[x][y] = new Node(String.valueOf(newNodeNum), newRiskLevel);
            }
        }

        // complete first row
        for (int y = 0; y < oldGridLength; y++) {
            for (int x = oldGridLength; x < gridX; x++) {
                Node node = newGrid[x-oldGridLength][y];

                int newNodeNum = (y * gridY) + x;
                int newRiskLevel = getNewRiskLevel(node.getRiskLevel());
                newGrid[x][y] = new Node(String.valueOf(newNodeNum), newRiskLevel);
            }
        }

        // complete remaining rows
        for (int y = oldGridLength; y < gridY; y++) {
            for (int x = 0; x < gridX; x++) {
                Node node = newGrid[x][y-oldGridLength];
                int newNodeNum = (y*gridY) + x;
                int newRiskLevel = getNewRiskLevel(node.getRiskLevel());
                newGrid[x][y] = new Node(String.valueOf(newNodeNum), newRiskLevel);
            }
        }

        return newGrid;
    }

    private static int getNewRiskLevel(int riskLevel) {
        if (riskLevel < 9) {
            return riskLevel + 1;
        } else {
            return 1;
        }
    }
}
