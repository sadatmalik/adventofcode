package com.sadatmalik.aoc.daynineteen;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    String name;
    Orientation orientation;
    Position pos;
    List<Beacon> beacons;

    static final List<Scanner> scanners = new ArrayList<>(); // all scanners

    Scanner(String name) {
        this.name = name;
        this.beacons = new ArrayList<>();
    }

    void addBeacon(Beacon beacon) {
        beacons.add(beacon);
    }

    public static void loadScanners(String filename) {
        List<String> data = FileReader.getDataFromFile(filename);
        Scanner scanner = null;
        for (String line : data) {
            if (line.equals("")) {
                continue;
            }

            if (line.startsWith("---")) {
                scanner = new Scanner(line);
                scanners.add(scanner);
            } else {
                String[] coordinates = line.split(",");
                Position pos = new Position(Integer.parseInt(coordinates[0]),
                        Integer.parseInt(coordinates[1]),
                        Integer.parseInt(coordinates[2]));
                Beacon beacon = new Beacon(pos);
                scanner.addBeacon(beacon);
            }
        }

        // set first scanner default orientation and position
        Scanner base = scanners.get(0);
        base.orientation = Orientation.FORWARD_UP;
        base.pos = new Position(0,0,0);
    }

    public static void printAll() {
        for (Scanner s : scanners) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(name + "\n");
        for (Beacon b : beacons) {
            sb.append(b + "\n");
        }
        return sb.toString();
    }
}

