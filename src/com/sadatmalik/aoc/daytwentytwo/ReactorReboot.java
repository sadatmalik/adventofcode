package com.sadatmalik.aoc.daytwentytwo;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReactorReboot {

    static List<Instruction> instructions = new ArrayList<>();
    static Set<Cuboid> on = new HashSet<>();
    static ReactorGrid reactor;

    public static void main(String[] args) {
        loadRebootData("data/daytwentytwo/testdata.txt");
        print(instructions);
        runInstructions(-50, -50, -50, -50, -50, -50);
    }

    private static void runInstructions(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
        reactor = new ReactorGrid(xMin, xMax, yMin, yMax, zMin, zMax);
        int step = 1;
        for (Instruction instruction : instructions) {
            execution(instruction);
            System.out.println("Cuboids lit after step " + step + " = " + on.size());
            step++;
        }
    }

    private static void execution(Instruction instruction) {
        for (int z = instruction.zStart; z <= instruction.zEnd; z++) {
            for (int y = instruction.yStart; y <= instruction.yEnd; y++) {
                for (int x = instruction.xStart; x <= instruction.xEnd; x++) {
                    if (instruction.turnOn) {
                        on.add(new Cuboid(x, y, z));
                    } else {
                        on.remove(new Cuboid(x, y, z));
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
