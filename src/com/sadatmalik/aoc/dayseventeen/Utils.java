package com.sadatmalik.aoc.dayseventeen;

public class Utils {

    static boolean notEnoughVelocityX(Velocity velocity, Position pos, Target target) {
        if (velocity.x == 0) {
            if (pos.x < target.left) {
                return true;
            }
        }
        return false;
    }

}
