package com.sadatmalik.aoc.daytwentytwo.test;

import com.sadatmalik.aoc.daytwentytwo.Cuboid;

import java.util.List;

public class TestStepSeven {
    public static void main(String[] args) {
        testStepSeven();
    }

    private static void testStepSeven() {
        //Adding the new cube insert: Cuboid{xMin=-27, xMax=23, yMin=-28, yMax=26, zMin=-21, zMax=29}
        Cuboid n = new Cuboid(-27, 23, -28, 26, -21, 29);

        //Removing overlapping cube: Cuboid{xMin=2, xMax=28, yMin=23, yMax=23, zMin=17, zMax=28}
        //Adding it's cube splits: Cuboid{xMin=24, xMax=28, yMin=23, yMax=23, zMin=17, zMax=28}
        Cuboid e = new Cuboid(2, 28, 23, 23, 17, 28);
        List<Cuboid> splits = e.splitAroundOverlap(n);
        print("first split result: ", splits);

    }

    private static void print(String msg, Cuboid c) {
        System.out.println(msg);
        System.out.println(c);
        System.out.println();
    }
    private static void print(String msg, List<Cuboid> split) {
        System.out.println(msg);
        for (Cuboid c : split) {
            System.out.println(c);
        }
        System.out.println();
    }
}
