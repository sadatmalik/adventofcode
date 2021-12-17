package com.sadatmalik.aoc.dayseventeen;

public class Target {
    int left;
    int right;
    int top;
    int bottom;

    // e.g. target area: x=20..30, y=-10..-5
    // e.g. target area: x=244..303, y=-91..-54
    public Target(int left, int right, int bottom, int top) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
}
