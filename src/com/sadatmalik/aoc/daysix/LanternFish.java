package com.sadatmalik.aoc.daysix;

public class LanternFish {
    int numDaysUntilCreatesNewFish;

    public LanternFish() {
        this.numDaysUntilCreatesNewFish = 8;
    }

    public LanternFish(int numDaysUntilCreatesNewFish) {
        this.numDaysUntilCreatesNewFish = numDaysUntilCreatesNewFish;
    }

    public LanternFish newDay() {
        if (numDaysUntilCreatesNewFish == 0) {
            numDaysUntilCreatesNewFish = 6;
            return new LanternFish();
        }
        numDaysUntilCreatesNewFish--;
        return null;
    }

    @Override
    public String toString() {
        return numDaysUntilCreatesNewFish + ", ";
    }
}
