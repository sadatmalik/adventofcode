package com.sadatmalik.aoc.daynineteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Beacon {
    Position pos;

    static final List<Beacon> allBeacons = new ArrayList<>();
    static final Map<Position, Beacon> beaconsByPosition = new HashMap();

    public Beacon(Position pos) {
        this.pos = pos;
    }

    // must be given a list of unique scanners all fwd_up normalised
    public static void collectUniqueBeacons(List<Scanner> scanners) {
        for (Scanner s : scanners) {
            for (Beacon b : s.beacons) {
                if (!beaconsByPosition.containsKey(b.pos)) {
                    beaconsByPosition.put(b.pos, b);
                    allBeacons.add(b);
                }
            }
        }
        System.out.println("\nTotal unique beacons = " + allBeacons.size());
    }

    @Override
    public String toString() {
        return "" + pos;
    }
}
