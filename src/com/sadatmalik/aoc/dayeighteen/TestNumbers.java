package com.sadatmalik.aoc.dayeighteen;

public class TestNumbers {

    public static void main(String[] args) {
        String number = "[1,2]";
        Number sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[1,2],3]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[9,[8,7]]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[1,9],[8,5]]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[[[1,2],[3,4]],[[5,6],[7,8]]],9]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);
    }
}
