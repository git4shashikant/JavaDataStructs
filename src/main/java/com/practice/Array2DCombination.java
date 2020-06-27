package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array2DCombination {
    public static void main(String[] args) {

        char[][] input = {{'a', 'b'}, {'c', 'q'}, {'d', 'e', 'f'}};
        System.out.println("unique permutations count using recursion: " + countRecursion(input));
        System.out.println("unique permutations count using dynamic programming: " + countDynamicProgramming(input));

        System.out.println("----------------------------------------------");
        System.out.println("Printing unique permutations Using String");
        System.out.println("-----------------------------------------------");
        List<String> combinations = combinations(input, 0);
        combinations.stream()
                .forEach(s -> System.out.println(s));

        System.out.println("----------------------------------------------");
        System.out.println("Printing unique permutations Using char array");
        System.out.println("-----------------------------------------------");
        char[][] chars = charCombinations(input, 0);
        for(int i = 0; i < chars.length; i++) {
            for(int j = 0; j < chars[i].length; j++) {
                System.out.print(chars[i][j]);
            }
            System.out.println("");
        }

    }

    public static int countRecursion(char[][] input) {
        if (input.length == 1) {
            return input[0].length;
        }

        int segment1 = countRecursion(Arrays.copyOfRange(input, 0, 1));
        int segment2 = countRecursion(Arrays.copyOfRange(input, 1, input.length));


        return segment1 * segment2;
    }

    public static int countDynamicProgramming(char[][] input) {
        int count = 1;
        for (int i = 0; i < input.length; i++) {
            count *= input[i].length;
        }

        return count;
    }

    public static List<String> combinations(char[][] input, int startIndex) {
        List<String> strings = new ArrayList<>();

        for(int i = 0; i < input[startIndex].length; i++) {
            String s = String.valueOf(input[startIndex][i]);
            if (startIndex == input.length - 1) {
                strings.add(s);
            } else {
                List<String> combinations = combinations(input, startIndex + 1);
                combinations.stream()
                        .forEach(str -> {
                            StringBuilder sb = new StringBuilder(s);
                            sb.append(str);
                            strings.add(sb.toString());
                        });
            }
        }

        return strings;
    }

    public static char[][] charCombinations(char[][] input, int startIndex) {
        char[][] copyOfRange = Arrays.copyOfRange(input, startIndex, input.length);
        int resultCount = countDynamicProgramming(copyOfRange);
        char[][] chars = new char[resultCount][copyOfRange.length];

        int resultRowIndex = 0;
        int resultColumnIndex = 0;

        if (startIndex < input.length) {
            for(int i = 0; i < input[startIndex].length; i++) {
                char[][] combinations = charCombinations(input, startIndex + 1);
                if (combinations.length == 0) {
                    chars[resultRowIndex][resultColumnIndex] = input[startIndex][i];
                    resultRowIndex++;
                    resultColumnIndex++;
                } else {
                    for (int j = 0; j < combinations.length; j++) {
                        chars[resultRowIndex][0] = input[startIndex][i];
                        int l = 1;
                        for (int k = 0; k < combinations[j].length; k++) {
                            chars[resultRowIndex][l] = combinations[j][k];
                            l++;
                        }

                        resultRowIndex++;
                    }
                }
            }
        }

        return chars;
    }
}
