package com.sadatmalik.aoc.daynineteen;

import java.util.List;

public class TestScanners {

    static List<Scanner> scanners;

    public static void main(String[] args) {
//        testLoadScanners("data/daynineteen/sampledata.txt");
//        testScannerMatch(3);

//        testLoadScanners("data/daynineteen/testdata.txt");
//        testScannerMatch(12);
//        testUniqueBacons();
//        testManhattanDistance();

        testLoadScanners("data/daynineteen/puzzledata.txt");
        testScannerMatch(12);
        testUniqueBacons();
        testManhattanDistance();
    }

    private static void testLoadScanners(String filename) {
        Scanner.loadScanners(filename);
        scanners = Scanner.scanners;
        Scanner.printAll();
    }

    private static void testScannerMatch(int seeking) {
        BeaconScanner.normaliseScanners(seeking);
    }

    private static void testUniqueBacons() {
        Beacon.collectUniqueBeacons(Scanner.normalisedScanners);
    }

    private static void testManhattanDistance() {
        int distance = Scanner.getGreatestManhattanDistance();
        System.out.println("Greatest manhattan distance = " + distance);
    }

}
