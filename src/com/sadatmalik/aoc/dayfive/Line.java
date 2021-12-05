package com.sadatmalik.aoc.dayfive;

public class Line {
    private Coordinate start;
    private Coordinate end;

    private boolean isHorizontal = false;
    private boolean isVertical = false;

    private Line(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
    }

    public static Line createLine(String[] startAndEnd) {
        String[] startPoint = startAndEnd[0].split(",");
        String[] endPoint = startAndEnd[1].split(",");

        int startX = Integer.parseInt(startPoint[0]);
        int startY = Integer.parseInt(startPoint[1]);

        if (startX > Grid.maxGridX) {
            Grid.maxGridX = startX;
        }
        if (startY > Grid.maxGridY) {
            Grid.maxGridY = startY;
        }
        Coordinate start = new Coordinate(startX, startY);

        int endX = Integer.parseInt(endPoint[0]);
        int endY = Integer.parseInt(endPoint[1]);

        if (endX > Grid.maxGridX) {
            Grid.maxGridX = endX;
        }
        if (endY > Grid.maxGridY) {
            Grid.maxGridY = endY;
        }
        Coordinate end = new Coordinate(endX, endY);

        Line line = new Line(start, end);

        if (startY == endY) {
            line.isHorizontal = true;
        }

        if (startX == endX) {
            line.isVertical = true;
        }

        return line;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isVertical() {
        return isVertical;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Line{" +
                "start=" + start +
                ", end=" + end);

        if (isHorizontal) {
            sb.append(", horizontal");
        } else if (isVertical) {
            sb.append(", vertical");
        } else {
            sb.append(", diagonal");
        }

        return sb.append("}").toString();

    }
}
