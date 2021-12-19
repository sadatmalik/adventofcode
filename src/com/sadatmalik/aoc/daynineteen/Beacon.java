package com.sadatmalik.aoc.daynineteen;

import java.util.ArrayList;
import java.util.List;

public class Beacon {
    Position pos;

    static final List<Beacon> allBeacons = new ArrayList<>();

    public Beacon(Position pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "" + pos;
    }
}
