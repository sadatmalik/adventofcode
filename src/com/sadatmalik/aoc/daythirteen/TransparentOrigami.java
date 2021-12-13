package com.sadatmalik.aoc.daythirteen;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransparentOrigami {

    static Grid grid;
    static ArrayList<String> dataPoints;
    static ArrayList<String> folds;

    public static void main(String[] args) {

        //1. with test data
        getDataFromFile("data/daythirteen/testdata.txt");
        grid = Grid.populateGrid(dataPoints);
        System.out.println(grid);
        origamiFolds();

        //2. with puzzle data
        getDataFromFile("data/daythirteen/puzzledata1.txt");
        grid = Grid.populateGrid(dataPoints);
        System.out.println(grid);
        origamiFolds();

    }

    private static void origamiFolds() {
        Grid foldedGrid = null;
        int foldNumber = 0;
        for (String fold : folds) {

            String trim = fold.replaceAll("fold along ", "");
            String[] foldAlong = trim.split("=");
            if (foldAlong[0].equals("y")) {
                foldedGrid = grid.foldY(Integer.parseInt(foldAlong[1]));

            } else if (foldAlong[0].equals("x")) {
                foldedGrid = grid.foldX(Integer.parseInt(foldAlong[1]));
            }

            System.out.println("\n" + ++foldNumber + ". Folded Grid:");
            System.out.print(foldedGrid);
            System.out.println("Visible dots = " + foldedGrid.getNumVisibleDots());
            grid = foldedGrid;
        }
    }

    public static void getDataFromFile(String filename) {

        dataPoints = new ArrayList<>();
        folds = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
            String line = br.readLine();
            while (line != null && !line.equals("")) {
                dataPoints.add(line);
                line = br.readLine();
            }
            
            line = br.readLine();
            
            while (line != null) {
                folds.add(line);
                line = br.readLine();
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
