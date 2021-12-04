package com.sadatmalik.aoc.dayfour;

public class BingoBoard {

    private boolean isWinner = false;
    public int[][] grid = new int[5][5];

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public void markBoard(int draw) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] == draw) {
                    grid[i][j] = -1;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            sb.append("\n[ ");
            for (int j = 0; j < 5; j++) {
                sb.append(grid[i][j]);
                if (j < 4) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");
            if (i == 4) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public boolean isWinningBoard() {
        // check for horizontal rows
        boolean winner = false;
        for (int i = 0; i < 5; i++) {

            if (winner) {
                break;
            }

            boolean possibleWinningRow = true;
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] != -1) {
                    possibleWinningRow = false;
                }
            }

            winner = possibleWinningRow;
        }

        // check for vertical columns
        if (!winner) {

            for (int i = 0; i < 5; i++) {

                if (winner) {
                    break;
                }

                boolean possibleWinningColumn = true;
                for (int j = 0; j < 5; j++) {
                    if (grid[j][i] != -1) {
                        possibleWinningColumn = false;
                    }
                }

                winner = possibleWinningColumn;
            }

        }

        isWinner = winner;
        return winner;
    }

    public int getUnmarkedSum() {
        int unmarkedSum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] != -1) {
                    unmarkedSum += grid[i][j];
                }
            }
        }
        return unmarkedSum;
    }
}
