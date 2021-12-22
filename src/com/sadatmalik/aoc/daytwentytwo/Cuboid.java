package com.sadatmalik.aoc.daytwentytwo;

import java.util.Objects;

public class Cuboid {
    int x;
    int y;
    int z;

    public Cuboid(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuboid cuboid = (Cuboid) o;
        return x == cuboid.x && y == cuboid.y && z == cuboid.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
