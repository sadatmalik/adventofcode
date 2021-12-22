package com.sadatmalik.aoc.daytwentyone;

public class Die {

    int numRolls = 0;
    int lastRoll = 0;

    int roll() {
        numRolls++;
        return (int) Math.ceil(++lastRoll % 100);
    }

    public int getNumRolls() {
        return numRolls;
    }
}
