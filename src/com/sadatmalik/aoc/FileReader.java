package com.sadatmalik.aoc;

import com.sadatmalik.aoc.dayfour.BingoBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {

    public static ArrayList<Integer> getBingoDraws(String filename) {
        ArrayList<Integer> draws = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
            String line = br.readLine();
            // read first line only
            String[] nums = line.split(",");
            for (int i = 0; i < nums.length; i++) {
                draws.add(Integer.parseInt(nums[i]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return draws;
    }

    public static ArrayList<BingoBoard> getBingoBoards(String filename) {
        ArrayList<BingoBoard> boards = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
            // skip first line
            String line = br.readLine();
            line = br.readLine();

            while (line != null) {
                // skip blank lines
                if (line.equals("")) {
                    line = br.readLine();
                }

                // load bingo board
                BingoBoard board = new BingoBoard();
                for (int i = 0; i < 5; i++) {
                    line = line.trim();
                    String[] bingoLine = line.split("\\s+");
                    for (int j = 0; j < 5; j++) {
                        //System.out.println(bingoLine[j]);
                        board.grid[i][j] = Integer.parseInt(bingoLine[j]);
                    }
                    line = br.readLine();
                }
                boards.add(board);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return boards;
    }


    public static ArrayList<String> getDataFromFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

}
