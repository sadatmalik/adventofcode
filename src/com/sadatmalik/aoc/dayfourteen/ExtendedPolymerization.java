package com.sadatmalik.aoc.dayfourteen;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtendedPolymerization {

    static String baseString;
    static Map<String, String> mappings;

    public static void main(String[] args) {
        // 1. with test data
        runPolymerization("data/dayfourteen/testdata.txt", 10, false);

        // 1. with puzzle data
        runPolymerization("data/dayfourteen/puzzledata1.txt", 10, false);

        // 2. with test data
        runPolymerization("data/dayfourteen/testdata.txt", 40, true);

        // 2. with puzzle data
        runPolymerization("data/dayfourteen/puzzledata1.txt", 40, true);

    }

    private static void runPolymerization(String filename, int steps, boolean partTwo) {
        getDataFromFile(filename);
        PolymerProcess polymerize = new PolymerProcess(baseString, mappings);
        if (!partTwo) {
            String polymer = polymerize.polymerize(steps);
            System.out.println("Polymerization result = " + CheckPolymer.process(polymer));
        } else {
            String lastPair = polymerize.polymerize2(steps);
            System.out.println("Polymerization result = " + CheckPolymer.process(lastPair, polymerize.pairsCountMap));
        }

    }

    public static void getDataFromFile(String filename) {

        mappings = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
            String line = br.readLine();
            while (line != null && !line.equals("")) {
                //read polymer template
                baseString = line;
                line = br.readLine();
            }

            line = br.readLine();

            //read pair insertion rules
            while (line != null) {
                String[] tokens = line.split(" -> ");
                mappings.put(tokens[0], tokens[1]);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
