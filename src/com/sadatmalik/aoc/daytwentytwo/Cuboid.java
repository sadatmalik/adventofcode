package com.sadatmalik.aoc.daytwentytwo;

import java.util.ArrayList;
import java.util.List;

public class Cuboid {

    int xMin;
    int xMax;

    int yMin;
    int yMax;

    int zMin;
    int zMax;

    CubicPoint topLeftBack;
    CubicPoint bottomLeftBack;

    CubicPoint topRightBack;
    CubicPoint bottomRightBack;

    CubicPoint topLeftForward;
    CubicPoint bottomLeftForward;

    CubicPoint topRightForward;
    CubicPoint bottomRightForward;


    public Cuboid(CubicPoint topLeftBack, CubicPoint bottomLeftBack, CubicPoint topRightBack, CubicPoint bottomRightBack,
                  CubicPoint topLeftForward, CubicPoint bottomLeftForward, CubicPoint topRightForward, CubicPoint bottomRightForward) {
        this.topLeftBack = topLeftBack;
        this.bottomLeftBack = bottomLeftBack;
        this.topRightBack = topRightBack;
        this.bottomRightBack = bottomRightBack;
        this.topLeftForward = topLeftForward;
        this.bottomLeftForward = bottomLeftForward;
        this.topRightForward = topRightForward;
        this.bottomRightForward = bottomRightForward;
    }

    public Cuboid(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.zMin = zMin;
        this.zMax = zMax;

        this.topLeftBack = new CubicPoint(xMin, yMax, zMin);
        this.bottomLeftBack = new CubicPoint(xMin, yMin, zMin);
        this.topRightBack = new CubicPoint(xMax, yMax, zMin);
        this.bottomRightBack = new CubicPoint(xMax, yMin, zMin);
        this.topLeftForward = new CubicPoint(xMin, yMax, zMax);
        this.bottomLeftForward = new CubicPoint(xMin, yMin, zMax);
        this.topRightForward = new CubicPoint(xMax, yMax, zMax);
        this.bottomRightForward = new CubicPoint(xMax, yMin, zMax);

    }

    public Cuboid overlaps(Cuboid cuboid) {

        Cuboid overlap = null;

        int xMax = Math.min(this.xMax, cuboid.xMax);
        int xMin = Math.max(this.xMin, cuboid.xMin);
        int xOverlap = xMax - xMin;

        int yMax = Math.min(this.yMax, cuboid.yMax);
        int yMin = Math.max(this.yMin, cuboid.yMin);
        int yOverlap = yMax - yMin;

        int zMin = Math.max(this.zMin, cuboid.zMin);
        int zMax = Math.min(this.zMax, cuboid.zMax);
        int zOverlap = zMax - zMin;

        if (xOverlap > 0 && yOverlap > 0 && zOverlap > 0) {
            overlap = new Cuboid(xMin, xMax, yMin, yMax, zMin, zMax);
        }

        return overlap;
    }

    // returns 3 cuboids removing the overlap
    public List<Cuboid> split(Cuboid overlap) {
        List<Cuboid> split = new ArrayList<>();

        // todo - need to take these down by '1' to account for overlapping sides not double counting in final count

        if (this.xMin < overlap.xMin) { // cut out from right
            if (this.yMin < overlap.yMin) { // cut out from right, top

                if (this.zMin < overlap.zMin) { // cut out from right, top, front
                    split.add(new Cuboid(xMin, overlap.xMin, yMin, yMax, zMin, zMax)); // left plane
                    split.add(new Cuboid(overlap.xMin, xMax, yMin, overlap.yMin, zMin, zMax)); // bottom right long
                    split.add(new Cuboid(overlap.xMin, xMax, overlap.yMin, yMax, zMin, overlap.zMin)); // top right back cube

                } else if (this.zMax > overlap.zMax) { // cut out from right, top, back
                    split.add(new Cuboid(xMin, overlap.xMin, yMin, yMax, zMin, zMax)); // left plane
                    split.add(new Cuboid(overlap.xMin, xMax, yMin, overlap.yMin, zMin, zMax)); // bottom right long
                    split.add(new Cuboid(overlap.xMin, xMax, overlap.yMin, yMax, overlap.zMax, zMax)); // top right front cube
                }
            } else if (this.yMax > overlap.yMax) { // cut out from right, bottom

                if (this.zMin < overlap.zMin) { // cut out from right, bottom, front
                    split.add(new Cuboid(xMin, overlap.xMin, yMin, yMax, zMin, zMax)); // left plane
                    split.add(new Cuboid(overlap.xMin, xMax, overlap.yMax, yMax, zMin, zMax)); // top right long
                    split.add(new Cuboid(overlap.xMin, xMax, yMin, overlap.yMax, zMin, overlap.zMin)); // bottom right back cube

                } else if (this.zMax > overlap.zMax) { // cut out from right, bottom, back
                    split.add(new Cuboid(xMin, overlap.xMin, yMin, yMax, zMin, zMax)); // left plane
                    split.add(new Cuboid(overlap.xMin, xMax, overlap.yMax, yMax, zMin, zMax)); // top right long
                    split.add(new Cuboid(overlap.xMin, xMax, yMin, overlap.yMax, overlap.zMax, zMax)); // bottom right front cube
                }
            }
        } else if (this.xMax > overlap.xMax) { // cut out from left
            if (this.yMin < overlap.yMin) { // cut out from left, top
                if (this.zMin < overlap.zMin) { // cut out from left, top, front
                    split.add(new Cuboid(overlap.xMax, xMax, yMin, yMax, zMin, zMax)); //right plane
                    split.add(new Cuboid(xMin, overlap.xMax, yMin, overlap.yMin, zMin, zMax)); //left bottom long
                    split.add(new Cuboid(xMin, overlap.xMax, overlap.yMin, yMax, zMin, overlap.zMin)); //top left back cube

                } else if (this.zMax > overlap.zMax) { // cut out from left, top, back
                    split.add(new Cuboid(overlap.xMax, xMax, yMin, yMax, zMin, zMax)); //right plane
                    split.add(new Cuboid(xMin, overlap.xMax, yMin, overlap.yMin, zMin, zMax)); //left bottom long
                    split.add(new Cuboid(xMin, overlap.xMax, overlap.yMin, yMax, overlap.zMax, zMax));//left top front cube
                }
            } else if (this.yMax > overlap.yMax) { // cut out from left, bottom
                if (this.zMin < overlap.zMin) { // cut out from left, bottom, front
                    split.add(new Cuboid(overlap.xMax, xMax, yMin, yMax, zMin, zMax)); //right plane
                    split.add(new Cuboid(xMin, overlap.xMax, overlap.yMax, yMax, zMin, zMax)); //left top long
                    split.add(new Cuboid(xMin, overlap.xMax, yMin, overlap.yMax, zMin, overlap.zMin)); //left bottom back cube

                } else if (this.zMax > overlap.zMax) { // cut out from left, bottom, back
                    split.add(new Cuboid(overlap.xMax+1, xMax, yMin, yMax, zMin, zMax)); //right plane
                    split.add(new Cuboid(xMin, overlap.xMax, overlap.yMax+1, yMax, zMin, zMax)); //left top long
                    split.add(new Cuboid(xMin, overlap.xMax, yMin, overlap.yMax, overlap.zMax+1, zMax));//left bottom front cube
                }
            }
        }

        return split;
    }

    private int checkOverlapVertex(Cuboid overlap) {
        if (this.topLeftBack == overlap.topLeftBack) return 0;
        else if (this.bottomLeftBack == overlap.bottomLeftBack) return 1;
        else if (this.topRightBack == overlap.topRightBack) return 2;
        else if (this.bottomRightBack == overlap.bottomRightBack) return 3;
        else if (this.topLeftForward == overlap.topLeftForward) return 4;
        else if (this.bottomLeftForward == overlap.bottomLeftForward) return 5;
        else if (this.topRightForward == overlap.topRightForward) return 6;
        else if (this.bottomRightForward == overlap.bottomRightForward) return 7;

        return -1;
    }


    @Override
    public String toString() {
        return "Cuboid{" +
                "xMin=" + xMin +
                ", xMax=" + xMax +
                ", yMin=" + yMin +
                ", yMax=" + yMax +
                ", zMin=" + zMin +
                ", zMax=" + zMax +
                '}';
    }

    public long volume() {
        return Math.abs((xMax-xMin+1L) * (yMax-yMin+1L) * (zMax-zMin+1L)); //the +1's count the surface as a point
    }
}
