package com.sadatmalik.aoc.dayseven;

import com.sadatmalik.aoc.FileReader;

import java.util.Arrays;

public class MinimumFuelCalculator {

    public static void main(String[] args) {
        // 1 & 2. with test data
        int[] startPositions = getStartPositions("data/dayseven/testdata.txt");
        System.out.println("Minimum consumption = " + calculateMinFuel(startPositions) + "\n");

        // 1 & 2. with puzzle data
        startPositions = getStartPositions("data/dayseven/puzzledata1.txt");
        System.out.println("Minimum consumption = " + calculateMinFuel(startPositions) + "\n");

    }

    private static int[] getStartPositions(String filename) {
        int[] positions = FileReader.getDataFromCSVFile(filename);
        System.out.println(Arrays.toString(positions));
        return positions;
    }

    private static int calculateMinFuel(int[] positions) {
        int max = (Arrays.stream(positions).max()).getAsInt();
        System.out.println("Max = " + max);

        int[] consumptions = new int[max];

        for (int i = 0; i < max; i++) {
            int fuel = 0;
            for (int j = 0; j < positions.length; j++) {
                // 1. fuel += diff(positions[j], i);
                // 2. fuel += diff2(positions[j], i);
                fuel += diff2(positions[j], i);
            }
            // System.out.println("Fuel at " + i + " = " + fuel);
            consumptions[i] = fuel;
        }

        return (Arrays.stream(consumptions).min()).getAsInt();
    }

    private static int diff2(int start, int end) {
        int increment = 1;
        int fuel = 0;
        while (start < end) {
            start++;
            fuel += increment;
            increment++;
        }
        while (start > end) {
            start--;
            fuel += increment;
            increment++;
        }

        return fuel;
    }

    private static int diff(int start, int end) {
        return Math.abs(start - end);
    }
}
