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
                if (s1 != s2 && !(checked.contains(s2))) {
                    int matches = ScannerCompare.match(s1, s2, seeking);
//                    if (matches == 0) {
//                        System.out.println("Matched beacons for " + s1.name + " and " + s2.name +
//                                " = " + matches);
//                    } else {
//                        System.out.println("Matched beacons for " + s1.name + " and " + s2.name +
//                                " = " + matches + ": " + ScannerCompare.matchedBeacons);
//                    }

                    if (matches >= 12) {
                        System.out.println("Matched beacons for " + s1.name + " and " + s2.name +
                                " = " + matches + ": " + ScannerCompare.matchedBeacons);

                        System.out.println("Unique beacons found = "
                                + ScannerCompare.uniqueBeaconsByPosition.size());
                    }
                }
            }
            checked.add(s1);
        }
    }
}
