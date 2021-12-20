package com.sadatmalik.aoc.daynineteen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestScanners {

    static List<Scanner> scanners;

    public static void main(String[] args) {
//        testLoadScanners("data/daynineteen/sampledata.txt");
//        testScannerMatch(3);

        testLoadScanners("data/daynineteen/testdata.txt");
        testScannerMatch(12);
        testUniqueBacons();
    }

    private static void testLoadScanners(String filename) {
        Scanner.loadScanners(filename);
        scanners = Scanner.scanners;
        Scanner.printAll();
    }

    private static void testScannerMatch(int seeking) {
        Set<Scanner> checked = new HashSet<>();
        for (Scanner s1 : scanners) {
            for (Scanner s2 : scanners) {
                //if (s1 != s2 && !(checked.contains(s2))) {
                if (s1 != s2) {
                    int matches = ScannerCompare.match(s1, s2, seeking);
                }
            }
            checked.add(s1);
        }
        for (Scanner s : scanners) {
            System.out.println(s.name + " position: " + s.pos);
        }
    }

    private static void testUniqueBacons() {
        Beacon.collectUniqueBeacons(Scanner.normalisedScanners);
    }

}
