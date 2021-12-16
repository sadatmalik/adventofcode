package com.sadatmalik.aoc.daysixteen;

public class PacketDecoder {

    // decodes to binary 1101 0010 1111 1110 0010 1000
    private static final String testHexString1 = "D2FE28";
    private static final String testOperatorPacket = "38006F45291200";
    private static final String testOperatorPacketTypeID1 = "EE00D40C823060";

    public static void main(String[] args) {
        String bin = hexToBin(testHexString1);
        System.out.println(bin);
        decode(bin);

        bin = hexToBin(testOperatorPacket);
        System.out.println(bin);
        decode(bin);

        bin = hexToBin(testOperatorPacketTypeID1);
        System.out.println(bin);
        decode(bin);

    }

    private static void decode(String bin) {
        char[] chars = bin.toCharArray();
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (i < chars.length) {
            // first 3 bits - packet version
            String versionBin = bin.substring(i, i+3);
            int versionDec = binToDec(versionBin);
            sb.append("Version: " + versionDec + ", ");
            i = i+3;

            // next 3 bits - packet type ID
            String typeIdBin = bin.substring(i, i+3);
            int typeIdDec = binToDec(typeIdBin);
            sb.append("TypeID: " + typeIdDec + ", ");
            i = i+3;

            switch (typeIdDec) {
                case 4:
                    //read 5 characters at a time
                    StringBuffer literalValue = new StringBuffer();
                    String fiveBits = null;
                    int packetsRead = 0;
                    do {
                        fiveBits = bin.substring(i, i+5);
                        i = i+5;
                        String valueBits = fiveBits.substring(1);
                        literalValue.append(valueBits);
                        packetsRead++;
                    } while ((fiveBits.charAt(0) != '0'));

                    int literalValueDec = binToDec(literalValue.toString());
                    sb.append("Literal Value = " + literalValueDec + ", ");
                    int totalBitsRead = 6 + (packetsRead * 5);
                    int skipBits = 4 - (totalBitsRead % 4);
                    String zeros = bin.substring(i, i + skipBits);
                    i = i + skipBits;

                    sb.append("Trailing zeroes = " + zeros + "\n");
                    break;

                default:
                    // read length type ID bit
                    String lengthTypeId = bin.substring(i, i+1);
                    i = i + 1;
                    sb.append("Length Type ID = " + lengthTypeId + ", ");
                    if (lengthTypeId.equals("0")) {
                        // next 15 bits represent total number of sub packet bits (length in bits)
                        String subPacketBitLength = bin.substring(i, i + 15);
                        i = i + 15;
                        int bitReadLength = binToDec(subPacketBitLength);
                        sb.append("Sub Packet bit read length = " + bitReadLength + ", ");

                        int numPackets = bitReadLength / 11;
                        int overflow = bitReadLength % 11;

                        sb.append("Literal Value = ");
                        for (int n = 0; n < numPackets; n++) {
                            if (n == numPackets-1) {
                                // read numPackets + overflow
                                String value = bin.substring(i, i + 11 + overflow);
                                i = i + 11 + overflow;
                                int decValue = binToDec(value);
                                sb.append(value + " " + "\n");

                                // skip
                                i = chars.length;

                                continue;
                            }
                            String value = bin.substring(i, i + 11);
                            i = i + 11;
                            int decValue = binToDec(value);
                            sb.append(value + " " + "\n");

                        }

                    } else {
                        // next 11 bits represent number of sub packet (length in packets)
                        String subPacketNumber = bin.substring(i, i + 11);
                        i = i + 11;
                        int numPacketsToRead = binToDec(subPacketNumber);
                        sb.append("Sub Packets to read = " + numPacketsToRead + ", ");

                        for (int n = 0; n < numPacketsToRead; n++) {
                            String value = bin.substring(i, i + 11);
                            i = i + 11;
                            int decValue = binToDec(value);
                            sb.append(value + " ");
                        }


                        sb.append("\n");
                        // skip
                        i = chars.length;

                        continue;

                    }


                    break;
            }


        }
        System.out.println(sb);
    }

    private static int binToDec(String bin) {
        return Integer.parseInt(bin,2);
    }

    private static String hexToBin(String hex){
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }
}
