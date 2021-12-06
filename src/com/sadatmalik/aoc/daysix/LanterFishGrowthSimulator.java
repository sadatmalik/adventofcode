package com.sadatmalik.aoc.daysix;

import com.sadatmalik.aoc.FileReader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class LanterFishGrowthSimulator {

    public static void main(String[] args) {
        // 1. with test data
//        int[] data = FileReader.getDataFromCSVFile("data/daysix/testdata.txt");
//        System.out.println(Arrays.toString(data));
//
//        int totalLanternFish = genCycle(data, 80);
//        System.out.println("\nTotal lantern fish = " + totalLanternFish + "\n");

        // 1. with puzzle data
//        data = FileReader.getDataFromCSVFile("data/daysix/puzzledata1.txt");
//        System.out.println(Arrays.toString(data));
//
//        totalLanternFish = genCycle(data, 80);
//        System.out.println("\nTotal lantern fish = " + totalLanternFish + "\n");

        // 2. test data with performance tunings
        int[] data = FileReader.getDataFromCSVFile("data/daysix/testdata.txt");
        System.out.println(Arrays.toString(data));

        // genCyclePerformance3(data, 256);

        // 2. puzzle data with performance tunings
        data = FileReader.getDataFromCSVFile("data/daysix/puzzledata1.txt");
        System.out.println(Arrays.toString(data));

        genCyclePerformance3(data, 256);

    }

    private static int genCycle(int[] data, int simDays) {
        int days = 1;
        ArrayList<LanternFish> fish = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            fish.add(new LanternFish(data[i]));
        }
        while (days <= simDays ) {
            ArrayList<LanternFish> babies = new ArrayList<>();
            for ( LanternFish f: fish ) {
                LanternFish baby = f.newDay();
                if (baby != null) {
                    babies.add(baby);
                }
            }
            fish.addAll(babies);
            System.out.println("Gen Cycle " + days + " complete");
            // System.out.println("After " + days + " days: " + fish);
            days++;
        }
        return fish.size();
    }

    private static int genCyclePerformance(int[] fish, int simDays) {
        int days = 1;

        while (days <= simDays ) {
            int babies = 0;
            for (int i = 0; i < fish.length; i++ ) {
                if (fish[i] == 0) {
                    fish[i] = 6;
                    babies++;
                } else {
                    fish[i]--;
                }
            }

            if (babies > 0) {
                int[] old = fish;
                fish = new int[fish.length + babies];
                for (int i = 0; i < old.length; i++) {
                    fish[i] = old[i];
                }
                for (int i = old.length; i < old.length + babies; i++) {
                    fish[i] = 8;
                }
            }
            System.out.println("Gen Cycle " + days + " complete");
            // System.out.println("After " + days + " days: " + Arrays.toString(fish));
            days++;
        }
        return fish.length;
    }

    private static void genCyclePerformance2(int[] startData, int simDays) {
        int days = 1;

        int[] fish = new int[9];
        for (int i = 0; i < startData.length; i++) {
            fish[startData[i]]++;
        }
        System.out.println("Day 0: " + Arrays.toString(fish) + "\n");

        while (days <= simDays ) {
            int babies = fish[0];
            for (int i = 0; i < 8; i++ ) {
                fish[i] = fish[i+1];
            }
            fish[6] += babies;
            fish[8] = babies;

            // System.out.println("Gen Cycle " + days + " complete");
            System.out.println("After " + days + " days: " + Arrays.toString(fish) +
                    " Total fish = " + getTotal(fish));
            days++;
        }
    }

    private static void genCyclePerformance3(int[] startData, int simDays) {
        int days = 1;

        BigDecimal[] fish = new BigDecimal[9];
        // initialize
        for (int i = 0; i <= 8; i++) {
            fish[i] = new BigDecimal(0);
        }


        for (int i = 0; i < startData.length; i++) {
            BigDecimal current = fish[startData[i]];
            BigDecimal inc = current.add(new BigDecimal(1));
            fish[startData[i]] = inc;
        }
        System.out.println("Day 0: " + Arrays.toString(fish) + "\n");

        while (days <= simDays ) {
            BigDecimal babies = fish[0];
            for (int i = 0; i < 8; i++ ) {
                fish[i] = fish[i+1];
            }
            fish[6] = fish[6].add(babies);
            fish[8] = babies;

            // System.out.println("Gen Cycle " + days + " complete");
            System.out.println("After " + days + " days: " + Arrays.toString(fish) +
                    " Total fish = " + getTotal(fish));
            days++;
        }
    }

    private static BigDecimal getTotal(BigDecimal[] fish) {
        // total fish
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i <= 8; i++) {
            BigDecimal inc = fish[i];
            total = total.add(inc);
        }
        return total;
    }

    private static BigDecimal getTotal(int[] fish) {
        // total fish
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i <= 8; i++) {
            BigDecimal inc = new BigDecimal(fish[i]);
            total = total.add(inc);
        }
        return total;
    }
}
