package com.sadatmalik.aoc.daynineteen;

import java.util.*;

public class BeaconScanner {

    public static void main(String[] args) {
        // load with puzzle data
        Scanner.loadScanners("data/daynineteen/puzzledata.txt");
        BeaconScanner.normaliseScanners(12);

        // part 1 - unique beacons
        Beacon.collectUniqueBeacons(Scanner.normalisedScanners);

        // part 2 - manhattan distance
        int distance = Scanner.getGreatestManhattanDistance();
        System.out.println("Greatest manhattan distance = " + distance);
    }


    //exposed for testing
    public static void normaliseScanners(int seeking) {
        List<Scanner> normalised = new ArrayList<>(Scanner.normalisedScanners);
        List<Scanner> notNormalised = new ArrayList<>(Scanner.notNormalisedScanners);

        int i = 0;
        while(Scanner.notNormalisedScanners.size() > 1) {
            System.out.println("Pass number #" + i++);
            for (Scanner s1 : normalised) {
                for (Scanner s2 : notNormalised) {
                    if (s1 != s2) {
                        int matches = ScannerCompare.match(s1, s2, seeking);
                    }
                }
            }

            normalised = new ArrayList<>(Scanner.normalisedScanners);
            notNormalised = new ArrayList<>(Scanner.notNormalisedScanners);

            for (Scanner s : Scanner.scanners) {
                System.out.print(s.name + " is related to ");
                for (Map.Entry<Scanner, Translation> rel : s.relatives.entrySet()) {
                    System.out.print(rel.getKey().name + " ");
                    System.out.print("[" + rel.getValue().pos + ", " + rel.getValue().or + "]");
                }
                System.out.println();
            }
            for (Scanner s : Scanner.scanners) {
                System.out.println(s.name + " position: " + s.pos);
            }
        }
    }
}
