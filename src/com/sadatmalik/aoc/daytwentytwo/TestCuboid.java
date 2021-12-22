package com.sadatmalik.aoc.daytwentytwo;

public class TestCuboid {

    public static void main(String[] args) {
        testCuboid();
    }

    private static void testCuboid() {
        Cuboid a = new Cuboid(1,5,1,4,1,6);
        Cuboid b = new Cuboid(4,7,0,3,2,4);

        Cuboid c = a.overlaps(b);

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
    }
}
