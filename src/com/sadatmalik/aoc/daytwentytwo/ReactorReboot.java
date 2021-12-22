package com.sadatmalik.aoc.daytwentytwo;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReactorReboot {

    static List<Instruction> instructions = new ArrayList<>();
    static Set<CubicPoint> on = new HashSet<>();
    static ReactorGrid reactor;

    static Set<Cuboid> litCuboids = new HashSet<>();

    public static void main(String[] args) {

        //part 1
        //setReactorDimensions(-50, 50, -50, 50, -50, 50);
        //loadRebootData("data/daytwentytwo/testdata.txt");
        //loadRebootData("data/daytwentytwo/testdata2.txt");
        //loadRebootData("data/daytwentytwo/puzzledata.txt");

        //part 2
        loadRebootData("data/daytwentytwo/testdata.txt");
        print(instructions);
        runInstructions2();
    }

    private static void runInstructions2() {
        int step = 1;
        for (Instruction i : instructions) {
            execution2(i);
            long lit = getLitCubaloids();
            System.out.println("Large cuboids in set after step " + step + " = " + litCuboids.size() +
                    ", total lit cubaloids = " + lit);
            step++;
        }
    }

    private static long getLitCubaloids() {
        long totalVol = 0;
        for (Cuboid cuboid : litCuboids) {
            totalVol += cuboid.volume();
        }
        return totalVol;
    }

    private static void execution2(Instruction i) {
        Cuboid cuboid = new Cuboid(i.xStart, i.xEnd, i.yStart, i.yEnd, i.zStart, i.zEnd);
        addCuboid(i.turnOn, cuboid);
    }

    private static void addCuboid(boolean turnOn, Cuboid c) {
        // todo - look for an exact match case

        // look for an overlap against existing lit cuboids
        List<Cuboid> addList = new ArrayList<>();
        List<Cuboid> removeList = new ArrayList<>();
        boolean foundOverlap = false;
        for (Cuboid lit : litCuboids) {
            Cuboid overlap = c.overlaps(lit);
            if (overlap == null) {
                continue;
            } else {
                foundOverlap = true;
                // need to split and add
                if (!turnOn) {
                    // split lit
                    List<Cuboid> split = lit.split(overlap);
                    for (Cuboid cs : split) {
                        addList.add(cs);
                    }
                    removeList.add(lit);
                } else {
                    // split c
                    List<Cuboid> split = c.split(overlap);
                    for (Cuboid cs : split) {
                        addCuboid(turnOn, cs);
                    }
                    return;
                }
            }
        }
        if (!foundOverlap) {
            litCuboids.add(c);
        }
        for (Cuboid clr : removeList) {
            litCuboids.remove(clr);
        }
        for (Cuboid cla : addList) {
            litCuboids.add(cla);
        }


        // add first one
        if (litCuboids.isEmpty()) {
            if (turnOn) {
                litCuboids.add(c);
            }
        }

    }

    private static void setReactorDimensions(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
        reactor = new ReactorGrid(xMin, xMax, yMin, yMax, zMin, zMax);
    }

    private static void runInstructions() {
        int step = 1;
        for (Instruction instruction : instructions) {
            execution(instruction);
            System.out.println("Cuboids lit after step " + step + " = " + on.size());
            step++;
        }
    }

    private static void execution(Instruction instruction) {
        for (int z = instruction.zStart; z <= instruction.zEnd; z++) {
            if (reactor != null && (z < reactor.zMin || z > reactor.zMax))
                return;

            for (int y = instruction.yStart; y <= instruction.yEnd; y++) {
                if (reactor != null && (y < reactor.yMin || y > reactor.yMax))
                    return;

                for (int x = instruction.xStart; x <= instruction.xEnd; x++) {
                    if (reactor != null && (x < reactor.xMin || x > reactor.xMax))
                        return;

                    if (instruction.turnOn) {
                        on.add(new CubicPoint(x, y, z));
                    } else {
                        on.remove(new CubicPoint(x, y, z));
                    }
                }
            }
        }
    }

    private static void print(List<Instruction> instructions) {
        for (Instruction i : instructions) {
            System.out.println(i);
        }
    }

    private static void loadRebootData(String filename) {
        List<String> data = FileReader.getDataFromFile(filename);
        for (String line : data) {

            String[] details = line.split(" ");
            boolean on = details[0].equals("on") ? true : false;

            String[] ranges = details[1].split(",");
            String xRange = ranges[0].replace("x=", "");
            String yRange = ranges[1].replace("y=", "");
            String zRange = ranges[2].replace("z=", "");

            int xStart = Integer.parseInt(xRange.split("\\.\\.")[0]);
            int xEnd = Integer.parseInt(xRange.split("\\.\\.")[1]);

            int yStart = Integer.parseInt(yRange.split("\\.\\.")[0]);
            int yEnd = Integer.parseInt(yRange.split("\\.\\.")[1]);

            int zStart = Integer.parseInt(zRange.split("\\.\\.")[0]);
            int zEnd = Integer.parseInt(zRange.split("\\.\\.")[1]);

            instructions.add(new Instruction(on, xStart, xEnd,
                    yStart, yEnd, zStart, zEnd));
        }
    }
}
