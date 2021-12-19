package com.sadatmalik.aoc.dayeighteen;

import com.sadatmalik.aoc.FileReader;

import java.util.List;

public class Homework {

    static void partOne(String filename) {
        List<String> data = FileReader.getDataFromFile(filename);
        Number number = new Number(data.get(0));
        System.out.println(number);
        for (int i = 1; i < data.size(); i++) {
            number.add(new Number(data.get(i)));
            System.out.println(number);
        }
    }

    static int largestMagnitude;
    static Number largestNumber;

    static void partTwo(String filename) {
        List<String> data = FileReader.getDataFromFile(filename);

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                Number a = new Number(data.get(i));
                Number b = new Number(data.get(j));
                a.add(b);
                int magnitude = a.magnitude();
                if (magnitude > largestMagnitude) {
                    largestMagnitude = magnitude;
                    largestNumber = a;
                }
            }
        }
        System.out.println("Largest magnitude number = " + largestNumber);
    }
}
