package com.sadatmalik.aoc.dayseven;

import com.sadatmalik.aoc.FileReader;

import java.util.Arrays;

public class MinimumFuelCalculator {

    public static void main(String[] args) {
        // 1. with test data
        getMinimumFuel("data/dayseven/testdata.txt");

        // 1. with puzzle data
        getMinimumFuel("data/dayseven/puzzledata1.txt");

    }

    private static void getMinimumFuel(String filename) {
        int[] positions = FileReader.getDataFromCSVFile(filename);
        System.out.println(Arrays.toString(positions));

        int[] consumptions = calculateFuel(positions);
        int max = (Arrays.stream(consumptions).max()).getAsInt();
        for (int i = 0; i < consumptions.length; i++) {
            if (consumptions[i] < max) {
                max = consumptions[i];
            }
        }
        System.out.println("\nMinimum fuel = " + max + "\n");
    }

    private static int[] calculateFuel(int[] positions) {
        int max = (Arrays.stream(positions).max()).getAsInt();
        System.out.println("Max = " + max);

        int[] consumptions = new int[max];

//        int fuelAtMax = 0;
//        for (int i = 0; i < positions.length; i++) {
//            fuelAtMax += diff(positions[i], max);
//        }
//        System.out.println("Fuel at " + max + " = " + fuelAtMax);
//        consumptions[max-1] = fuelAtMax;
//
//        int fuelAtOne = 0;
//        for (int i = 0; i < positions.length; i++) {
//            fuelAtOne += diff(positions[i], 1);
//        }
//        System.out.println("Fuel at 1 = " + fuelAtOne);
//        consumptions[0] = fuelAtOne;

        for (int i = 0; i <= max; i++) {
            int fuel = 0;
            for (int j = 0; j < positions.length; j++) {
                fuel += diff(positions[j], i);
            }
            System.out.println("Fuel at " + i + " = " + fuel);
            consumptions[i] = fuel;
        }

        return consumptions;
    }

    private static int diff(int start, int end) {
        return Math.abs(start - end);
    }
}
