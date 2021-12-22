package com.sadatmalik.aoc.daytwentytwo;

public class Instruction {
    boolean turnOn;

    int xStart;
    int xEnd;

    int yStart;
    int yEnd;

    int zStart;
    int zEnd;

    public Instruction(boolean turnOn, int xStart, int xEnd, int yStart, int yEnd, int zStart, int zEnd) {
        this.turnOn = turnOn;
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.zStart = zStart;
        this.zEnd = zEnd;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "turnOn=" + turnOn +
                ", xStart=" + xStart +
                ", xEnd=" + xEnd +
                ", yStart=" + yStart +
                ", yEnd=" + yEnd +
                ", zStart=" + zStart +
                ", zEnd=" + zEnd +
                "}";
    }
}
