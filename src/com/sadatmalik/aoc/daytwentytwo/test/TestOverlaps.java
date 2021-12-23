package com.sadatmalik.aoc.daytwentytwo.test;

import com.sadatmalik.aoc.daytwentytwo.Cuboid;

import java.util.List;

public class TestOverlaps {

    public static void main(String[] args) {
        testOverlaps();
        //testSplitAroundOverlaps();
    }

    private static void testSplitAroundOverlaps() {
        // split in centre
        Cuboid c = new Cuboid(1,3,1,3,1,3);
        Cuboid d = new Cuboid(2,2,2,2,2,2);

        List<Cuboid> split = c.splitAroundOverlap(d);
        print("Split in centre, expect 6 response cubes: ", split); // looks good

        // 1. split in right-top-front
        d = new Cuboid(3,3,3,3,3,3);
        split = c.splitAroundOverlap(d);
        print("Split in right-top-front, expect 3 splits: ", split); // looks good

        // 2. split in right-top-back
        d = new Cuboid(3,3,3,3,1,1);
        split = c.splitAroundOverlap(d);
        print("Split in right-top-back, expect 3 splits: ", split); // looks good

        // 3. split in right-bottom-front
        d = new Cuboid(3,3,1,1,3,3);
        split = c.splitAroundOverlap(d);
        print("Split in right-bottom-front, expect 3 splits: ", split); // looks good

        // 4. split in right-bottom-back
        d = new Cuboid(3,3,1,1,1,1);
        split = c.splitAroundOverlap(d);
        print("Split in right-bottom-back, expect 3 splits: ", split); // looks good




        // 1. split in left-top-front
        d = new Cuboid(1,1,3,3,3,3);
        split = c.splitAroundOverlap(d);
        print("1. Split in left-top-front, expect 3 splits: ", split); // looks good

        // 2. split in left-top-back
        d = new Cuboid(1,1,3,3,1,1);
        split = c.splitAroundOverlap(d);
        print("2. Split in left-top-back, expect 3 splits: ", split); // looks good

        // 3. split in left-bottom-front
        d = new Cuboid(1,1,1,1,3,3);
        split = c.splitAroundOverlap(d);
        print("3. Split in left-bottom-front, expect 3 splits: ", split); // looks good

        // 4. split in left-bottom-back
        d = new Cuboid(1,1,1,1,1,1);
        split = c.splitAroundOverlap(d);
        print("4. Split in left-bottom-back, expect 3 splits: ", split); // looks good



        // entire overlap
        d = new Cuboid(1,3,1,3,1,3);
        split = c.splitAroundOverlap(d);
        print("0. Full overlap, expect 0 splits: ", split); // looks good



        // left-top-long
        d = new Cuboid(1,1,3,3,1,3);
        split = c.splitAroundOverlap(d);
        print("0. left-top-long, expect 2 splits: ", split); // looks good


        // long through centre forward/back
        d = new Cuboid(2,2,2,2,1,3);
        split = c.splitAroundOverlap(d);
        print("0. long through centre, expect 4 splits: ", split); // looks good

        // long through left centre forward/back
        d = new Cuboid(1,1,2,2,1,3);
        split = c.splitAroundOverlap(d);
        print("1. long through left centre, expect 3 splits: ", split); // looks good


        // chop bottom slice
        d = new Cuboid(1,3,1,1,1,3);
        split = c.splitAroundOverlap(d);
        print("1. chop bottom slice, expect 1 split: ", split); // looks good

        // chop centre slice
        d = new Cuboid(1,3,2,2,1,3);
        split = c.splitAroundOverlap(d);
        print("1. chop bottom slice, expect 2 splits: ", split); // looks good

        // top-down centre long
        d = new Cuboid(2,2,1,3,2,2);
        split = c.splitAroundOverlap(d);
        print("1. top-down centre long, expect 4 splits: ", split); // looks good

        // left-right centre long
        d = new Cuboid(1,3,2,2,2,2);
        split = c.splitAroundOverlap(d);
        print("1. left-right centre long, expect 4 splits: ", split); // looks good

    }

    private static void testOverlaps() {
        Cuboid c = new Cuboid(1,3,1,3,1,3);

        Cuboid d = new Cuboid(2,2,2,2,2,2);
        Cuboid o = c.overlaps(d);
        print("expecting centre overlap", o);

        d = new Cuboid(1,3,3,3,1,1);
        o = c.overlaps(d);
        print("Touching top back edge, expect top back edge overlap:", o);
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
