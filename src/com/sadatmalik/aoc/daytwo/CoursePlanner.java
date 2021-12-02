package com.sadatmalik.aoc.daytwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CoursePlanner {


    public static void main(String[] args) {
        // 1. with test data
        ArrayList commands = getTestCommandsFromFile("daytwo/testdata1.txt");
        System.out.println(commands.toString());
        System.out.println("Result = " + calculateResult(commands));

        // 1. with puzzle data
        commands = getTestCommandsFromFile("daytwo/puzzledata1.txt");
        System.out.println(commands.toString());
        System.out.println("Result = " + calculateResult(commands));

        // 2. with test data
        commands = getTestCommandsFromFile("daytwo/testdata1.txt");
        System.out.println(commands.toString());
        System.out.println("Result = " + calculateResultUsingAim(commands));

        // 2. with puzzle data
        commands = getTestCommandsFromFile("daytwo/puzzledata2.txt");
        System.out.println(commands.toString());
        System.out.println("Result = " + calculateResultUsingAim(commands));

    }

    private static int calculateResultUsingAim(ArrayList<Command> commands) {
        int horizontal = 0;
        int aim = 0;
        int depth = 0;

        for (Command c: commands) {
            switch (c.direction) {
                case 'f':
                    horizontal += c.distance;
                    depth += aim * c.distance;
                    break;
                case 'd':
                    aim += c.distance;
                    break;
                case 'u':
                    aim -= c.distance;
                    break;
            }
        }
        return horizontal * depth;
    }


    private static int calculateResult(ArrayList<Command> commands) {
        int horizontal = 0;
        int depth = 0;

        for (Command c: commands) {
            switch (c.direction) {
                case 'f':
                    horizontal += c.distance;
                    break;
                case 'd':
                    depth += c.distance;
                    break;
                case 'u':
                    depth -= c.distance;
                    break;
            }
        }
        return horizontal * depth;
    }


    private static ArrayList<Command> getTestCommandsFromFile(String filename) {
        ArrayList<Command> commands = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                String[] instruction = line.split(" ");
                Command command = new Command();
                command.direction = instruction[0].charAt(0);
                command.distance = Integer.parseInt(instruction[1]);
                commands.add(command);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return commands;
    }

    private static class Command {
        char direction; // f, d, u
        int distance;

        @Override
        public String toString() {
            return "{" + direction + ":" + distance + '}';
        }
    }

}
