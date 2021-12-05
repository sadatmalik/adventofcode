package com.sadatmalik.aoc.dayfive;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;

public class HydrothermalVentTracker {

    public static void main(String[] args) {
        // 1. with test data
        int overlaps = getOverlaps("data/dayfive/testdata.txt");
        System.out.println("Overlaps = " + overlaps);

        // 1. with puzzle data
//        overlaps = getOverlaps("data/dayfive/puzzledata1.txt");
//        System.out.println("Overlaps = " + overlaps);

        // 2. with puzzle data
        overlaps = getOverlaps("data/dayfive/puzzledata2.txt");
        System.out.println("Overlaps = " + overlaps);

    }

    private static int getOverlaps(String filename) {
        ArrayList<String> dataLines = FileReader.getDataFromFile(filename);
        System.out.println(dataLines);

        // parse data lines
        ArrayList<Line> lines = parseData(dataLines);

        // set up grid
        System.out.println("Grid size = " + Grid.maxGridX + ", " + Grid.maxGridY);
        Grid grid = new Grid();
        System.out.println(grid);

        for (Line line : lines) {
            // horizontal and vertical lines only - part 1
            // if (line.isHorizontal() || line.isVertical()) {

            // switching to all lines including diagonal for part 2:
                System.out.println("Adding line: " + line);
                grid.add(line);
                System.out.println(grid);

            // }
        }

        // get overlapping points:
        return grid.getNumOverlapPoints();
    }

    private static ArrayList<Line> parseData(ArrayList<String> dataLines) {
        ArrayList<Line> lines = new ArrayList<>();
        // split on -> then ,
        for (String s : dataLines) {
            String[] startAndEnd = s.split(" -> ");
            System.out.println(startAndEnd[0] + "|" + startAndEnd[1]);

            // Create line
            Line line = Line.createLine(startAndEnd);
            System.out.println(line);
            lines.add(line);
        }

        return lines;
    }
}
