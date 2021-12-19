package com.sadatmalik.aoc.daynineteen;

import java.util.ArrayList;
import java.util.List;

public class ScannerCompare {

    // return matched beacons?
    static List<Beacon> match(Scanner a, Scanner b) {
        List<Beacon> matchedBeacons = new ArrayList<>();

        // looking to see if 12 or more beacons align
        int aligned = 0;

        // cycle through up to 24 times comparing one beacon from b
        // against all beacons from a



        // if found 12 or more matches, return matched, else return empty
        if (aligned >= 12) {
            return  matchedBeacons;
        } else {
            return null;
        }
    }
}
