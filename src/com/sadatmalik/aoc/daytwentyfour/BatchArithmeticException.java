package com.sadatmalik.aoc.daytwentyfour;

public class BatchArithmeticException extends Exception {
    String msg;

    public BatchArithmeticException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
