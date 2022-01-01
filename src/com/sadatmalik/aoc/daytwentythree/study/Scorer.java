package com.sadatmalik.aoc.daytwentythree.study;

public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);
}