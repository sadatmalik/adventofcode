package com.sadatmalik.aoc.daynineteen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeaconScanner {

    public static void main(String[] args) {
        Scanner.loadScanners("data/daynineteen/puzzledata.txt");
        List<Scanner> scanners = Scanner.scanners;

        Set<Scanner> checked = new HashSet<>();
        for (Scanner s1 : scanners) {
            for (Scanner s2 : scanners) {
                //if (s1 != s2 && !(checked.contains(s2))) {
                if (s1 != s2) {
                    int matches = ScannerCompare.match(s1, s2, 12);
                }
            }
            checked.add(s1);
        }
        for (Scanner s : scanners) {
            System.out.println(s.name + " position: " + s.pos);
        }

        Beacon.collectUniqueBeacons(Scanner.normalisedScanners);
    }


}
