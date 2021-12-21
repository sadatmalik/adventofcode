package com.sadatmalik.aoc.daynineteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compares the beacons for scannerA against the beacons for scannerB seeking matches
public class ScannerCompare {

    static Scanner scannerA;
    static Scanner scannerB;
    static List<Position> matchedBeacons;
    static int seekCount;

    static final Map<Position, Beacon> uniqueBeaconsByPosition = new HashMap<>();

    static final Map<Scanner, List<Scanner>> waitingOn = new HashMap<>();

    // return number of matched beacons
    static int match(Scanner a, Scanner b, int seekCount) {
        initialize(a, b, seekCount);
        runMatch();
        return matchedBeacons.size();
    }

    private static void runMatch() {
        // cycle through every possible orientation pattern
        for (Orientation or : Orientation.values()) {
            for (Beacon bBeacon : scannerB.beacons) {
                Position bPos = bBeacon.pos;
                Position normalisedBPos = or.normalise(bPos); // normalise to forward_up

                for (Beacon aBeacon : scannerA.beacons) { // find diff between bPos and every aPos
                    Position aPos = aBeacon.pos;
                    Position diff = difference(aPos, normalisedBPos);

                    int matches = findBeaconMatches(diff, or); // find matches by testing every bPos shifted by diff against every aPos

                    if (matches >= seekCount) {
                        //System.out.println(scannerB.name + " relative to " + scannerA.name + " is: " + diff);
                        scannerB.addRelative(scannerA, diff, or);

                        // normalise and collect normalised scanners
                        // only proceed if relative scanner been already normalised (i.e. A)
                        if (!(Scanner.normalisedScanners.contains(scannerB)) &&
                            Scanner.normalisedScanners.contains(scannerA)) {

                            setAbsScannerPosition(scannerB, diff, or);

                            Scanner.normalisedScanners.add(scannerB);
                            Scanner.notNormalisedScanners.remove(scannerB);
                            return;
                        }
                    }
                }
            }
        }
    }

    private static void setAbsScannerPosition(Scanner scannerB, Position diff, Orientation or) {
        scannerB.pos = diff;
        for (Beacon b : scannerB.beacons) {
            Position normalised = or.normalise(b.pos);
            normalised.x = normalised.x + diff.x;
            normalised.y = normalised.y + diff.y;
            normalised.z = normalised.z + diff.z;

            b.pos = normalised;
        }
        System.out.println("Normalised: " + scannerB.name);
    }

    private static int findBeaconMatches(Position diff, Orientation or) {
        matchedBeacons = new ArrayList<>(); // reset matches
        int aligned = 0;

        for (Beacon bbBeacon : scannerB.beacons) {
            Position normalised = or.normalise(bbBeacon.pos);
            Position possMatch = applyDifference(normalised, diff); // translate bPos by diff

            for (Beacon aaBeacon : scannerA.beacons) {
                if (isMatch(aaBeacon.pos, possMatch)) { // test translated bPos against every aPos
                    aligned++;
                    matchedBeacons.add(possMatch);
                }
            }
            if (aligned >= seekCount) {
                return aligned;
            }
        }
        return aligned;
    }

    private static void initialize(Scanner a, Scanner b, int s) {
        scannerA = a;
        scannerB = b;
        seekCount = s;

        // initialize unique beacons
        for (Beacon beacon : a.beacons) {
            uniqueBeaconsByPosition.put(beacon.pos, beacon);
        }
    }

    private static boolean isMatch(Position aaPos, Position possMatch) {
        if (aaPos.x == possMatch.x
                && aaPos.y == possMatch.y
                && aaPos.z == possMatch.z) {
            return true;
        }
        return false;
    }

    private static Position applyDifference(Position bbPos, Position diff) {
        int x = bbPos.x + diff.x;
        int y = bbPos.y + diff.y;
        int z = bbPos.z + diff.z;

        Position possMatch = new Position(x, y, z);
        return  possMatch;
    }

    private static Position difference(Position aPos, Position bPos) {
        int x = aPos.x - bPos.x;
        int y = aPos.y - bPos.y;
        int z = aPos.z - bPos.z;

        Position diff = new Position(x, y, z);
        return diff;
    }
}
