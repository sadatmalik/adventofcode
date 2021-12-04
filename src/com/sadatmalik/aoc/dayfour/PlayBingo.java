package com.sadatmalik.aoc.dayfour;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;

public class PlayBingo {

    public static void main(String[] args) {

        // 1. with test data
        ArrayList<Integer> draws = FileReader.getBingoDraws("data/dayfour/testdata.txt");
        System.out.println("Test draw = " + draws);

        ArrayList<BingoBoard> boards = FileReader.getBingoBoards("data/dayfour/testdata.txt");
        System.out.println("Boards = " + boards);

        findWinningBoard(draws, boards);


        // 1. with puzzle data
        draws = FileReader.getBingoDraws("data/dayfour/puzzledata1.txt");
        System.out.println("Test draw = " + draws);

        boards = FileReader.getBingoBoards("data/dayfour/puzzledata1.txt");
        System.out.println("Boards = " + boards);

        findWinningBoard(draws, boards);

        // 2. with test data
        draws = FileReader.getBingoDraws("data/dayfour/testdata.txt");
        System.out.println("Test draw = " + draws);

        boards = FileReader.getBingoBoards("data/dayfour/testdata.txt");
        System.out.println("Boards = " + boards);

        while (boards.size() > 1) {
            findWinningBoard(draws, boards);
            removeWinningBoard(boards);
        }
        findWinningBoard(draws, boards);

        // 2. with puzzle data
        draws = FileReader.getBingoDraws("data/dayfour/puzzledata2.txt");
        System.out.println("Test draw = " + draws);

        boards = FileReader.getBingoBoards("data/dayfour/puzzledata2.txt");
        System.out.println("Boards = " + boards);

        while (boards.size() > 1) {
            findWinningBoard(draws, boards);
            removeWinningBoard(boards);
        }
        findWinningBoard(draws, boards);


    }

    private static void removeWinningBoard(ArrayList<BingoBoard> boards) {
        for (BingoBoard board : boards) {
            if (board.isWinner()) {
                boards.remove(board);
                break;
            }
        }
    }

    private static BingoBoard findWinningBoard(ArrayList<Integer> draws, ArrayList<BingoBoard> boards) {
        BingoBoard winner = null;

        for (Integer draw : draws) {
            boolean foundWinner = false;
            for (BingoBoard board : boards) {
                board.markBoard(draw);
                if(board.isWinningBoard()) {
                    winner = board;
                    foundWinner = true;
                    break;
                }
            }
            if (foundWinner) {
                System.out.println("Marked Boards = " + boards);
                System.out.println("Winning Board = " + winner);

                int unmarkedSum = winner.getUnmarkedSum();
                System.out.println("Unmarked sum = " + unmarkedSum);
                System.out.println("Last draw = " + draw);

                int result = unmarkedSum * draw;
                System.out.println("Result score = " + result);

                break;
            }
        }

        return winner;
    }


}
