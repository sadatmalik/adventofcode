package com.sadatmalik.aoc.daytwentytwo;

import java.util.Objects;

public class CubicPoint {
    int x;
    int y;
    int z;

    public CubicPoint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubicPoint cuboid = (CubicPoint) o;
        return x == cuboid.x && y == cuboid.y && z == cuboid.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
