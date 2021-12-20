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

    public Position normalise(Position pos) {
        int x = pos.x;
        int y = pos.y;
        int z = pos.z;
        switch(this) {  // working with a sample 'normalised' point at 1, -3, 2
            case FORWARD_UP:
                x = pos.x; //1 -> 1
                y = pos.y; //-3 -> -3
                z = pos.z; //2 -> 2
                break;

            case FORWARD_DOWN:
                x = pos.x; //1 -> 1
                y = -(pos.y); //3 -> -3
                z = -(pos.z); //-2 -> 2
                break;

            case FORWARD_LEFT:
                x = pos.x; //1 -> 1
                y = pos.z; //-3 -> -3
                z = -(pos.y); //-2 -> 2
                break;

            case FORWARD_RIGHT:
                x = pos.x; //1 -> 1
                y = -(pos.z); //3 -> -3
                z = pos.y; //2 -> 2
                break;


            case BACKWARD_UP:
                x = -(pos.x); //-1 -> 1
                y = pos.y; //-3 -> -3
                z = -(pos.z); //-2 -> 2
                break;

            case BACKWARD_DOWN:
                x = -(pos.x); //-1 -> 1
                y = -(pos.y); //3 -> -3
                z = pos.z; //2 -> 2
                break;

            case BACKWARD_LEFT:
                x = -(pos.x); //-1 -> 1
                y = -(pos.z); //3 -> -3
                z = -(pos.y); //-2 -> 2
                break;

            case BACKWARD_RIGHT:
                x = -(pos.x); //-1 -> 1
                y = pos.z;  //-3 -> 3
                z = pos.y; //2 -> 2
                break;


            case LEFT_UP:
                x = pos.z; // 1 -> 1
                y = pos.y; //-3 -> 3
                z = -(pos.x); //-2 -> 2
                break;

            case LEFT_DOWN:
                x = -(pos.z); //-1 -> 1
                y = -(pos.y); //3 -> -3
                z = -(pos.x); //-2 -> 2
                break;

            case LEFT_FORWARD:
                x = pos.y; //1 -> 1
                y = -(pos.z); //3 -> -3
                z = -(pos.x); //-2 -> 2
                break;

            case LEFT_BACKWARD:
                x = -(pos.y); //-1 -> 1
                y = pos.z; //-3 -> -3
                z = -(pos.x); //-2 -> 2
                break;


            case RIGHT_UP:
                x = -(pos.z); //-1 -> 1
                y = pos.y; //-3 -> 3
                z = pos.x; //2 -> 2
                break;

            case RIGHT_DOWN:
                x = pos.z; //1 -> 1
                y = -(pos.y); //3 -> -3
                z = pos.x; //2 -> 2
                break;

            case RIGHT_FORWARD:
                x = pos.y; //1 -> 1
                y = pos.z; //-3 -> -3
                z = pos.x; //2 -> 2
                break;

            case RIGHT_BACKWARD:
                x = -(pos.y); //-1 -> 1
                y = -(pos.z); //3 -> -3
                z = pos.x; //2 -> 2
                break;


            case UP_FORWARD:
                x = pos.y; //1 -> 1
                y = pos.x; //-3 -> -3
                z = -(pos.z); //-2 -> 2
                break;

            case UP_BACKWARD:
                x = -(pos.y); //-1 -> 1
                y = pos.x; //-3 -> -3
                z = pos.z; //2 -> 2
                break;

            case UP_LEFT:
                x = -(pos.z); //-1 -> 1
                y = pos.x; //-3 -> -3
                z = -(pos.y); //-2 -> 2
                break;

            case UP_RIGHT:
                x = pos.z; //1 -> 1
                y = pos.x; //-3 -> -3
                z = pos.y; //2 -> 2
                break;


            case DOWN_FORWARD:
                x = pos.y; //1 -> 1
                y = -(pos.x); //3 -> -3
                z = pos.z; //2 -> 2
                break;

            case DOWN_BACKWARD:
                x = -(pos.y); //-1 -> 1
                y = -(pos.x); //3 -> -3
                z = -(pos.z); //-2 -> 2
                break;

            case DOWN_LEFT:
                x = pos.z; //1 -> 1
                y = -(pos.x); //3 -> -3
                z = -(pos.y); //-2 -> 2
                break;

            case DOWN_RIGHT:
                x = -(pos.z); //-1 -> 1
                y = -(pos.x); //3 -> -3
                z = pos.y; //2 -> 2
                break;

        }

        Position normalisedPos = new Position(x,y,z);
        return normalisedPos;
    }
}
