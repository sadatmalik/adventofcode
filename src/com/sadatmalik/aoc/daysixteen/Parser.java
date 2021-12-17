package com.sadatmalik.aoc.daysixteen;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    static String hex;
    static String binary;
    static int cursor;
    static int versionSum;

    public static void main(String[] args) {
        hex = Data.puzzlePacket;
        cursor = 0;
        versionSum = 0;
        parse();
        System.out.println("Version sum = " + versionSum);
    }

    private static void parse() {
        binary = Utils.hexToBin(hex);
        int length = binary.length();

        while (cursor < length) {
            int start = cursor;
            long value = parsePacket();
            System.out.println("Result: " + value);
            int bitsRead = cursor - start;
            cursor = cursor + (8 - (bitsRead % 8));
        }
    }

    private static long parsePacket() {
        int version = readInt(3);
        versionSum += version;

        int typeId = readInt(3);

        switch(typeId) {
            case 0:
                return parseSum();

            case 1:
                return parseProduct();

            case 2:
                return parseMin();

            case 3:
                return parseMax();

            case 4:
                return parseLiteral();

            case 5:
                return parseGreaterThan();

            case 6:
                return parseLessThan();

            case 7:
                return parseEquals();

            default:
                return 0;
        }
    }

    private static long parseLiteral() {
        String checkBit = readString(1);

        StringBuilder result = new StringBuilder();
        while(checkBit.equals("1")) {
            String read = readString(4);
            result.append(read);
            checkBit = readString(1);
        }
        String read = readString(4);
        result.append(read);

        return Utils.binToLong(result.toString());
    }

    private static long parseSum() {
        List<Long> values = parseOp();
        long result = 0;
        for (Long value : values) {
            result += value;
        }
        return result;
    }

    private static long parseProduct() {
        List<Long> values = parseOp();
        long result = 1;
        for (Long value : values) {
            result *= value;
        }
        return result;
    }

    private static long parseMin() {
        List<Long> values = parseOp();
        long result = Long.MAX_VALUE;
        for (Long value : values) {
            if (value < result)
                result = value;
        }
        return result;
    }

    private static long parseMax() {
        List<Long> values = parseOp();
        long result = Long.MIN_VALUE;
        for (Long value : values) {
            if (value > result)
                result = value;
        }
        return result;
    }

    private static long parseGreaterThan() {
        List<Long> values = parseOp();
        if (values.get(0) > values.get(1))
            return 1;
        return 0;
    }

    private static long parseLessThan() {
        List<Long> values = parseOp();
        if (values.get(0) < values.get(1))
            return 1;
        return 0;
    }

    private static long parseEquals() {
        List<Long> values = parseOp();
        if (values.get(0).equals(values.get(1)))
            return 1;
        return 0;
    }

    private static List<Long> parseOp() {
        List<Long> values = new ArrayList<>();
        int lengthType = readInt(1);
        if (lengthType == 0) {
            int subPacketsLength = readInt(15);
            int end = cursor + subPacketsLength;
            while (cursor != end) {
                values.add(parsePacket());
            }
        }
        if (lengthType == 1) {
            int numSubPackets = readInt(11);
            for (int n = 0; n < numSubPackets; n++) {
                values.add(parsePacket());
            }
        }
        return values;
    }

    private static String readString(int numBits) {
        String read = binary.substring(cursor, cursor + numBits);
        cursor = cursor + numBits;
        return read;
    }

    private static int readInt(int numBits) {
        String read = readString(numBits);
        int value = Utils.binToDec(read);
        return value;
    }

    private static long readLong(int numBits) {
        String read = readString(numBits);
        long value = Utils.binToDec(read);
        return value;
    }

}
