package com.sadatmalik.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {

    public static ArrayList<String> getDataFromFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

}
