package com.sadatmalik.aoc.daynineteen;

import java.util.List;

public class TestScanners {

    public static void main(String[] args) {
        testLoadScanners();
    }

    private static void testLoadScanners() {
        Scanner.loadScanners("data/daynineteen/testdata.txt");
        List<Scanner> scanners = Scanner.scanners;
        Scanner.printAll();
    }
}
