package com.sadatmalik.aoc.daytwentytwo.twodimensions;


// test logic with 2D to start with
public class Rectangle {

    Point topLeft;
    Point bottomLeft;

    Point topRight;
    Point bottomRight;

    public Rectangle(Point topLeft, Point bottomLeft, Point topRight, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
    }

    public Rectangle(int xMin, int xMax, int yMin, int yMax) {
        this.topLeft = new Point(xMin, yMax);
        this.bottomLeft = new Point(xMin, yMin);
        this.topRight = new Point(xMax, yMax);
        this.bottomRight = new Point(xMax, yMin);
    }



    public Rectangle overlaps(Rectangle rect) {

        Rectangle overlap = null;

        int xMax = Math.min(this.bottomRight.x, rect.bottomRight.x);
        int xMin = Math.max(this.bottomLeft.x, rect.bottomLeft.x);
        int xOverlap = xMax - xMin;

        int yMin = Math.max(this.bottomLeft.y, rect.bottomLeft.y);
        int yMax = Math.min(this.topLeft.y, rect.topLeft.y);
        int yOverlap = yMax - yMin;

        if (xOverlap > 0 && yOverlap >= 0) {
            overlap = new Rectangle(xMin, xMax, yMin, yMax);
        }

        return overlap;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "topLeft=" + topLeft +
                ", bottomLeft=" + bottomLeft +
                ", topRight=" + topRight +
                ", bottomRight=" + bottomRight +
                '}';
    }
}
