package com.sadatmalik.aoc.daysixteen;

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
            String value = parsePacket();
            System.out.println("Read: " + value);
            int bitsRead = cursor - start;
            cursor = cursor + (8 - (bitsRead % 8));
        }
    }

    private static String parsePacket() {
        int version = readInt(3);
        versionSum += version;

        int typeId = readInt(3);

        switch(typeId) {
            case 4:
                return parseLiteral();

            default:
                return parseOp();
        }
    }

    private static String parseLiteral() {
        String checkBit = readString(1);

        StringBuilder result = new StringBuilder();
        while(checkBit.equals("1")) {
            String read = readString(4);
            result.append(read);
            checkBit = readString(1);
        }
        String read = readString(4);
        result.append(read);

        return String.valueOf(Utils.binToLong(result.toString()));
    }

    private static String parseOp() {
        StringBuilder result = new StringBuilder();
        int lengthType = readInt(1);
        if (lengthType == 0) {
            int subPacketsLength = readInt(15);
            int end = cursor + subPacketsLength;
            while (cursor != end) {
                result.append(parsePacket());
            }
        }
        if (lengthType == 1) {
            int numSubPackets = readInt(11);
            for (int n = 0; n < numSubPackets; n++) {
                result.append(parsePacket());
            }
        }
        return result.toString();
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
