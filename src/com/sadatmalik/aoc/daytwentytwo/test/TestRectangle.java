package com.sadatmalik.aoc.daytwentytwo.test;

import com.sadatmalik.aoc.daytwentytwo.Rectangle;

public class TestRectangle {

    public static void main(String[] args) {
        testRectangle();
    }

    private static void testRectangle() {

        Rectangle a = new Rectangle(1, 5, 1, 4);
        Rectangle b = new Rectangle(4, 7, 0, 3);

        Rectangle c = a.overlaps(b);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
    }
}
