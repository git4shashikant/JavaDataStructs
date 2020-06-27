package com.practice;

/*
* Use dynamic programming:
*
*   A A A A S T R I N G 1
* S 0 0 0 0 1 0 0 0 0 0 0
* T 0 0 0 0 0 2 0 0 0 0 0
* R 0 0 0 0 0 0 3 0 0 0 0
* I 0 0 0 0 0 0 0 4 0 0 0
* N 0 0 0 0 0 0 0 0 5 0 0
* G 0 0 0 0 0 0 0 0 0 6 0
* 2 0 0 0 0 0 0 0 0 0 0 0
*
* 6  is the max size, you can track the max value with the index to know string value.
*
* formula: if char matched, array[i][j] = 1 + array[i-1][j-1]
*          else array[i][j] = 0
* */
public class LongestCommonSubString {

    public static void main(String[] args) {
        System.out.println(commonChild("ASAAString1", "String2"));
    }

    static String commonChild(String s1, String s2) {
        int maxI = 0;
        int max = 0;
        int[][] arr = new int[s1.length()+1][s2.length()+1];
        for (int i =  0; i < s1.length()+1; i++) {
            for (int j =  0; j < s2.length()+1; j++) {
                if (i == 0 || j == 0) {
                    arr[i][j] = 0;
                } else {
                    if (s1.charAt(i-1) == s2.charAt(j-1)) {
                        arr[i][j] = arr[i-1][j-1] + 1;
                        if (max < arr[i][j]) {
                            System.out.println("added:" + arr[i][j] + ", " + i+ ", " + j);
                            max = arr[i][j];
                            maxI = i;
                        }
                    } else {
                        arr[i][j] = 0;
                    }
                }
            }
        }

        char[] chars = new char[max];
        while(max > 0) {
            chars[max-1] = s1.charAt(maxI-1);
            maxI--;
            max--;
        }

        return String.valueOf(chars);
    }
}
