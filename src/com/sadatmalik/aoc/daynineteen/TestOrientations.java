package com.sadatmalik.aoc.daynineteen;

public class TestOrientations {

    public static void main(String[] args) {
        testNormalisations();
    }

    private static void testNormalisations() {
        Position fwdUp = new Position(1, -3, 2);
        normalise(Orientation.FORWARD_UP, fwdUp);

        Position fwdDown = new Position(1, 3, -2);
        normalise(Orientation.FORWARD_DOWN, fwdDown);

        Position fwdLeft = new Position(1, -2, -3);
        normalise(Orientation.FORWARD_LEFT, fwdLeft);

        Position fwdRight = new Position(1, 2, 3);
        normalise(Orientation.FORWARD_RIGHT, fwdRight);

        System.out.println();

        Position bwdUp = new Position(-1, -3, -2);
        normalise(Orientation.BACKWARD_UP, bwdUp);

        Position bwdDown = new Position(-1, 3, 2);
        normalise(Orientation.BACKWARD_DOWN, bwdDown);

        Position bwdLeft = new Position(-1, -2, 3);
        normalise(Orientation.BACKWARD_LEFT, bwdLeft);

        Position bwdRight = new Position(-1, 2, -3);
        normalise(Orientation.BACKWARD_RIGHT, bwdRight);

        System.out.println();

        Position leftUp = new Position(-2, -3, 1);
        normalise(Orientation.LEFT_UP, leftUp);

        Position leftDown = new Position(-2, 3, -1);
        normalise(Orientation.LEFT_DOWN, leftDown);

        Position leftFwd = new Position(-2, 1, 3);
        normalise(Orientation.LEFT_FORWARD, leftFwd);

        Position leftBwd = new Position(-2, -1, -3);
        normalise(Orientation.LEFT_BACKWARD, leftBwd);

        System.out.println();

        Position rightUp = new Position(2, -3, -1);
        normalise(Orientation.RIGHT_UP, rightUp);

        Position rightDown = new Position(2, 3, 1);
        normalise(Orientation.RIGHT_DOWN, rightDown);

        Position rightFwd = new Position(2, 1, -3);
        normalise(Orientation.RIGHT_FORWARD, rightFwd);

        Position rightBwd = new Position(2, -1, 3);
        normalise(Orientation.RIGHT_BACKWARD, rightBwd);

        System.out.println();

        Position upFwd = new Position(-3, 1, -2);
        normalise(Orientation.UP_FORWARD, upFwd);

        Position upBwd = new Position(-3, -1, 2);
        normalise(Orientation.UP_BACKWARD, upBwd);

        Position upLeft = new Position(-3, -2, -1);
        normalise(Orientation.UP_LEFT, upLeft);

        Position upRight = new Position(-3, 2, 1);
        normalise(Orientation.UP_RIGHT, upRight);

        System.out.println();

        Position downFwd = new Position(3, 1, 2);
        normalise(Orientation.DOWN_FORWARD, downFwd);

        Position downBwd = new Position(3, -1, -2);
        normalise(Orientation.DOWN_BACKWARD, downBwd);

        Position downLeft = new Position(3, -2, 1);
        normalise(Orientation.DOWN_LEFT, downLeft);

        Position downRight = new Position(3, 2, -1);
        normalise(Orientation.DOWN_RIGHT, downRight);

    }

    private static void normalise(Orientation orientation, Position measuredPos) {
        Position normalised = orientation.normalise(measuredPos);
        System.out.println(orientation + ": " + measuredPos + " normalises to: " + normalised);
    }

}
