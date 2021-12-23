package com.sadatmalik.aoc.daytwentytwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cuboid {

    int xMin;
    int xMax;
    int yMin;
    int yMax;
    int zMin;
    int zMax;

    public Cuboid(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.zMin = zMin;
        this.zMax = zMax;
    }

    public List<Cuboid> splitAroundOverlap(Cuboid cuboid) {
        Cuboid overlap = this.overlaps(cuboid);
        if (overlap == null) {
            return null;
        } else {
            List<Cuboid> split = new ArrayList<>();

            // break into 6 mini cuboids following same pattern
            // left plane
            if (xMin < overlap.xMin) {
                split.add(new Cuboid(xMin, overlap.xMin-1, yMin, yMax, zMin, zMax));
            }

            // right plane
            if (xMax > overlap.xMax) {
                split.add(new Cuboid(overlap.xMax + 1, xMax, yMin, yMax, zMin, zMax));
            }

            // top centre long
            if (yMax > overlap.yMax) {
                split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMax+1, yMax, zMin, zMax));
            }

            // bottom (centre) long
            if (yMin < overlap.yMin) {
                split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, overlap.yMin-1, zMin, zMax));
            }

            // front (centre/column/plane) block
            if (zMax > overlap.zMax) {
                split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMin, overlap.yMax, overlap.zMax+1, zMax));
            }

            // back (centre/column/plane) block
            if (zMin < overlap.zMin) {
                split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMin, overlap.yMax, zMin, overlap.zMin-1));
            }

            return split;
        }
    }

    public Cuboid overlaps(Cuboid cuboid) {
        Cuboid overlap = null;

        int xMin = Math.max(this.xMin, cuboid.xMin);
        int xMax = Math.min(this.xMax, cuboid.xMax);

        int yMin = Math.max(this.yMin, cuboid.yMin);
        int yMax = Math.min(this.yMax, cuboid.yMax);

        int zMin = Math.max(this.zMin, cuboid.zMin);
        int zMax = Math.min(this.zMax, cuboid.zMax);

        if (xMin <= xMax && yMin <= yMax && zMin <= zMax) {
            overlap = new Cuboid(xMin, xMax, yMin, yMax, zMin, zMax);
        }
        return overlap;
    }

    // returns 3 cuboids removing the overlap
    @Deprecated
    // doesn't work for enclosed overlaps - and way to complicated to figure out permutations this way
    // back to the drawing board - found a more elegant solution - see splitAroundOverlap() -SM 23/12/21
//    public List<Cuboid> split(Cuboid overlap) {
//        List<Cuboid> split = new ArrayList<>();
//
//        if (this.xMin < overlap.xMin && this.xMax > overlap.xMax) { // enclosed
//
//            // enclosed in x will always have a right and left plane
//            split.add(new Cuboid(xMin, overlap.xMin-1, yMin, yMax, zMin, zMax)); // left plane
//            split.add(new Cuboid(overlap.xMax+1, xMax, yMin, yMax, zMin, zMax)); //right plane
//
//            // could be a long through the centre
//            // therefore enclosed by a top centre long and a bottom centre long
//            if (this.zMin == overlap.zMin && this.zMax == overlap.zMax) { // no front or back cube to insert
//                if (this.yMin < overlap.yMin) { // return a bottom centre long
//                    split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, overlap.yMin - 1, zMin, zMax));
//                }
//                if (this.yMax > overlap.yMax) { // return a top centre long
//                    split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMax + 1, yMax, zMin, zMax));
//                }
//            } else if (this.zMin < overlap.zMin && this.zMax == overlap.zMax) { // needs a back centre cube
//                if (this.yMin < overlap.yMin) { // return a bottom centre long
//                    split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, overlap.yMin - 1, zMin, zMax));
//                }
//                if (this.yMax > overlap.yMax) { // return a top centre long
//                    split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMax + 1, yMax, zMin, zMax));
//                }
//                split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMin, overlap.yMax, zMin, overlap.zMin-1));
//
//            } else if (this.zMin == overlap.zMin && this.zMax > overlap.zMax) { // needs a front cube
//                if (this.yMin < overlap.yMin) { // return a bottom centre long
//                    split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, overlap.yMin - 1, zMin, zMax));
//                }
//                if (this.yMax > overlap.yMax) { // return a top centre long
//                    split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMax + 1, yMax, zMin, zMax));
//                }
//                split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMin, overlap.yMax, overlap.zMax+1, zMax));
//            } else if (this.zMin < overlap.zMin && this.zMax > overlap.zMax) { // need front and back cubes
//                if (this.yMin < overlap.yMin) { // return a bottom centre long
//                    split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, overlap.yMin - 1, zMin, zMax));
//                }
//                if (this.yMax > overlap.yMax) { // return a top centre long
//                    split.add(new Cuboid(overlap.xMin, overlap.xMax, overlap.yMax + 1, yMax, zMin, zMax));
//                }
//                split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, yMax, zMin, overlap.zMin-1));
//                split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, yMax, overlap.zMax+1, zMax));
//            }
//
//            // could be a floating hole out the centre
//            // then returning a left plane and a right plane
//            // and a top centre long and a bottom centre long
//            // and a front centre cube and a back centre cube
////            if (this.zMin < overlap.zMin) { // return a back centre cube -- todo - can this be a column?
////                split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, yMax, zMin, overlap.zMin-1));
////            }
////            if (this.zMax > overlap.zMax) { // return a front centre cube
////                split.add(new Cuboid(overlap.xMin, overlap.xMax, yMin, yMax, overlap.zMax+1, zMax));
////            }
//
//        }
//        // todo - I changed all the > to >= below - but need to think through if that's correct
//        else if (this.xMin < overlap.xMin) { // cut out from right
//            if (this.yMin < overlap.yMin) { // cut out from right, top
//
//                if (this.zMin < overlap.zMin) { // cut out from right, top, front
//                    split.add(new Cuboid(xMin, overlap.xMin-1, yMin, yMax, zMin, zMax)); // left plane
//                    split.add(new Cuboid(overlap.xMin, xMax, yMin, overlap.yMin-1, zMin, zMax)); // bottom right long
//                    split.add(new Cuboid(overlap.xMin, xMax, overlap.yMin, yMax, zMin, overlap.zMin-1)); // top right back cube
//
//                } else if (this.zMax >= overlap.zMax) { // cut out from right, top, back
//                    split.add(new Cuboid(xMin, overlap.xMin-1, yMin, yMax, zMin, zMax)); // left plane
//                    split.add(new Cuboid(overlap.xMin, xMax, yMin, overlap.yMin-1, zMin, zMax)); // bottom right long
//                    split.add(new Cuboid(overlap.xMin, xMax, overlap.yMin, yMax, overlap.zMax+1, zMax)); // top right front cube
//                }
//            } else if (this.yMax >= overlap.yMax) { // cut out from right, bottom
//
//                if (this.zMin < overlap.zMin) { // cut out from right, bottom, front
//                    split.add(new Cuboid(xMin, overlap.xMin-1, yMin, yMax, zMin, zMax)); // left plane
//                    split.add(new Cuboid(overlap.xMin, xMax, overlap.yMax+1, yMax, zMin, zMax)); // top right long
//                    split.add(new Cuboid(overlap.xMin, xMax, yMin, overlap.yMax, zMin, overlap.zMin-1)); // bottom right back cube
//
//                } else if (this.zMax >= overlap.zMax) { // cut out from right, bottom, back
//                    split.add(new Cuboid(xMin, overlap.xMin-1, yMin, yMax, zMin, zMax)); // left plane
//                    split.add(new Cuboid(overlap.xMin, xMax, overlap.yMax+1, yMax, zMin, zMax)); // top right long
//                    split.add(new Cuboid(overlap.xMin, xMax, yMin, overlap.yMax, overlap.zMax+1, zMax)); // bottom right front cube
//                }
//            }
//        } else if (this.xMax >= overlap.xMax) { // cut out from left
//            if (this.yMin < overlap.yMin) { // cut out from left, top
//                if (this.zMin < overlap.zMin) { // cut out from left, top, front
//                    split.add(new Cuboid(overlap.xMax+1, xMax, yMin, yMax, zMin, zMax)); //right plane
//                    split.add(new Cuboid(xMin, overlap.xMax, yMin, overlap.yMin-1, zMin, zMax)); //left bottom long
//                    split.add(new Cuboid(xMin, overlap.xMax, overlap.yMin, yMax, zMin, overlap.zMin-1)); //top left back cube
//
//                } else if (this.zMax >= overlap.zMax) { // cut out from left, top, back
//                    split.add(new Cuboid(overlap.xMax+1, xMax, yMin, yMax, zMin, zMax)); //right plane
//                    split.add(new Cuboid(xMin, overlap.xMax, yMin, overlap.yMin-1, zMin, zMax)); //left bottom long
//                    split.add(new Cuboid(xMin, overlap.xMax, overlap.yMin, yMax, overlap.zMax+1, zMax));//left top front cube
//                }
//            } else if (this.yMax >= overlap.yMax) { // cut out from left, bottom
//                if (this.zMin < overlap.zMin) { // cut out from left, bottom, front
//                    split.add(new Cuboid(overlap.xMax+1, xMax, yMin, yMax, zMin, zMax)); //right plane
//                    split.add(new Cuboid(xMin, overlap.xMax, overlap.yMax+1, yMax, zMin, zMax)); //left top long
//                    split.add(new Cuboid(xMin, overlap.xMax, yMin, overlap.yMax, zMin, overlap.zMin-1)); //left bottom back cube
//
//                } else if (this.zMax >= overlap.zMax) { // cut out from left, bottom, back
//                    split.add(new Cuboid(overlap.xMax+1, xMax, yMin, yMax, zMin, zMax)); //right plane
//                    split.add(new Cuboid(xMin, overlap.xMax, overlap.yMax+1, yMax, zMin, zMax)); //left top long
//                    split.add(new Cuboid(xMin, overlap.xMax, yMin, overlap.yMax, overlap.zMax+1, zMax));//left bottom front cube
//                }
//            }
//        }
//
//        return split;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuboid cuboid = (Cuboid) o;
        return xMin == cuboid.xMin && xMax == cuboid.xMax && yMin == cuboid.yMin && yMax == cuboid.yMax && zMin == cuboid.zMin && zMax == cuboid.zMax;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xMin, xMax, yMin, yMax, zMin, zMax);
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
