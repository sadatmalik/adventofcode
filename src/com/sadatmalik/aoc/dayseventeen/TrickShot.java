package com.sadatmalik.aoc.dayseventeen;

public class TrickShot {

    static Target target;
    static Position pos;
    static Velocity velocity;

    static boolean insufficientX;
    static boolean overshotY;
    static boolean overshotX;

    static int peak;

    public static void main(String[] args) {
        // sample data: target area: x=20..30, y=-10..-5
        //target = new Target(20, 30, -10, -5);

        // puzzle data: target area: x=244..303, y=-91..-54
        target = new Target(244, 303, -91, -54);
        findBestTrickShot(100, 100);

    }

    private static void findBestTrickShot(int xRange, int yRange) {
        int[][] peakHeights = new int[xRange][yRange];
        for (int x = 0; x < xRange; x++) {
            for (int y = 0; y < yRange; y++) {
                velocity = new Velocity(x, y);
                int peakHeight = runSimulation();
                peakHeights[x][y] = peakHeight;
            }
        }
        printPeaks(peakHeights, xRange, yRange);
        System.out.println("Heighest shot = " + peak);
    }

    private static void printPeaks(int[][] peakHeights, int xRange, int yRange) {
        for (int y = 0; y < yRange; y++) {
            for (int x = 0; x < yRange; x++) {
                System.out.printf("%4d ", peakHeights[x][y]);
            }
            System.out.println();
        }
    }

    private static int runSimulation() {
        pos = new Position(0,0);
        int peakHeight = 0;
        while(!shotTooFar()) {
            step();
            if (pos.y > peakHeight)
                peakHeight = pos.y;
            if (onTarget()) {
                //System.out.print("Landed at = " + pos);
                //System.out.println(", Peak height = " + peakHeight);
                if (peakHeight > peak)
                    peak = peakHeight;
                break;
            }
        }
        if (shotTooFar()) {
            //System.out.println("Overshot = " + pos + " " + velocity);
            peakHeight = 0;
        }
        return peakHeight;
    }

    static boolean shotTooFar() {
        if (pos.x > target.right) {
            return true;
        }
        if (pos.y < target.bottom) {
            return true;
        }
        return false;
    }

    private static boolean onTarget() {
        if ((pos.x >= target.left && pos.x <= target.right) &&
                (pos.y >= target.bottom && pos.y <= target.top)) {
            return true;
        }
        return false;
    }

    private static void step() {
        pos.x = pos.x + velocity.x;
        pos.y = pos.y + velocity.y;
        velocity.xTowardZero();
        velocity.decreaseY();
    }

    private static void runTests() {
        velocity = new Velocity(7, 2);
        runSimulation();

        velocity = new Velocity(6, 3);
        runSimulation();

        velocity = new Velocity(9, 0);
        runSimulation();

        velocity = new Velocity(17, -4);
        runSimulation();
    }

}
