package com.sadatmalik.aoc.dayeighteen;

import com.sadatmalik.aoc.FileReader;

import java.util.List;

public class SnailFish {

    public static void main(String[] args) {
        // with puzzle homework
        List<String> data = FileReader.getDataFromFile("data/dayeighteen/puzzledata.txt");
        Number number = new Number(data.get(0));
        System.out.println(number);
        for (int i = 1; i < data.size(); i++) {
            number.add(new Number(data.get(i)));
            System.out.println(number);
        }
    }
}
