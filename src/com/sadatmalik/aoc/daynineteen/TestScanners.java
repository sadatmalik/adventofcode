package com.sadatmalik.aoc.daynineteen;

import java.util.List;
import java.util.Map;

public class TestScanners {

    static List<Scanner> scanners;

    public static void main(String[] args) {
//        testLoadScanners("data/daynineteen/sampledata.txt");
//        testScannerMatch(3);

//        testLoadScanners("data/daynineteen/testdata.txt");
//        testScannerMatch(12);
//        testUniqueBacons();

        testLoadScanners("data/daynineteen/puzzledata.txt");
        testScannerMatch(12);
        testUniqueBacons();
    }

    private static void testLoadScanners(String filename) {
        Scanner.loadScanners(filename);
        scanners = Scanner.scanners;
        Scanner.printAll();
    }

    private static void testScannerMatch(int seeking) {

        List<Scanner> notNormalised = Scanner.notNormalisedScanners;
        List<Scanner> normalised = Scanner.normalisedScanners;

        int i = 0;
        while(Scanner.notNormalisedScanners.size() > 1) {
            System.out.println("Pass number #" + i++);
            for (Scanner s1 : scanners) {
                for (Scanner s2 : scanners) {
                    if (s1 != s2) {
                        int matches = ScannerCompare.match(s1, s2, seeking);
                    }
                }
            }


            for (Scanner s : scanners) {
                System.out.print(s.name + " is related to ");
                for (Map.Entry<Scanner, Translation> rel : s.relatives.entrySet()) {
                    System.out.print(rel.getKey().name + " ");
                    System.out.print("[" + rel.getValue().pos + ", " + rel.getValue().or + "]");
                }
                System.out.println();
            }
            for (Scanner s : scanners) {
                System.out.println(s.name + " position: " + s.pos);
            }
        }
    }

    private static void testUniqueBacons() {
        Beacon.collectUniqueBeacons(Scanner.normalisedScanners);
    }

}
