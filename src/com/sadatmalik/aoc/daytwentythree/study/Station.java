package com.sadatmalik.aoc.daytwentythree.study;

public class Station implements GraphNode {
    private final String id;
    private final String name;
    private final double latitude;
    private final double longitude;

    public Station(String id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String getId() {
        return null;
    }

    public String getName() {
        return name;
    }
}