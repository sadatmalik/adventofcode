package com.sadatmalik.aoc.daynineteen;

public enum Orientation {

    FORWARD_UP(Direction.FORWARD, Direction.UP),
    FORWARD_DOWN(Direction.FORWARD, Direction.DOWN),
    FORWARD_LEFT(Direction.FORWARD, Direction.LEFT),
    FORWARD_RIGHT(Direction.FORWARD, Direction.RIGHT),

    BACKWARD_UP(Direction.BACKWARD, Direction.UP),
    BACKWARD_DOWN(Direction.BACKWARD, Direction.DOWN),
    BACKWARD_LEFT(Direction.BACKWARD, Direction.LEFT),
    BACKWARD_RIGHT(Direction.BACKWARD, Direction.RIGHT),

    UP_FORWARD(Direction.UP, Direction.FORWARD),
    UP_BACKWARD(Direction.UP, Direction.BACKWARD),
    UP_RIGHT(Direction.UP, Direction.RIGHT),
    UP_LEFT(Direction.UP, Direction.LEFT),

    DOWN_FORWARD(Direction.DOWN, Direction.FORWARD),
    DOWN_BACKWARD(Direction.DOWN, Direction.BACKWARD),
    DOWN_RIGHT(Direction.DOWN, Direction.RIGHT),
    DOWN_LEFT(Direction.DOWN, Direction.LEFT),

    LEFT_FORWARD(Direction.LEFT, Direction.FORWARD),
    LEFT_BACKWARD(Direction.LEFT, Direction.BACKWARD),
    LEFT_UP(Direction.LEFT, Direction.UP),
    LEFT_DOWN(Direction.LEFT, Direction.DOWN),

    RIGHT_FORWARD(Direction.RIGHT, Direction.FORWARD),
    RIGHT_BACKWARD(Direction.RIGHT, Direction.BACKWARD),
    RIGHT_UP(Direction.RIGHT, Direction.UP),
    RIGHT_DOWN(Direction.RIGHT, Direction.DOWN);

    Direction facing;
    Direction rotation;

    Orientation(Direction facing, Direction rotation) {
        this.facing = facing;
        this.rotation = rotation;
    }
}
