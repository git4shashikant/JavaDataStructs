package com.practice;

import java.math.BigInteger;

public class Test {

    public static void main(String[] args) {
        int[][] array = new int [4][3];
        array[0][0] = 1;
        array[0][1] = 5;
        array[0][2] = 3;
        array[1][0] = 4;
        array[1][1] = 8;
        array[1][2] = 7;
        array[2][0] = 6;
        array[2][1] = 9;
        array[2][2] = 1;
        arrayManipulation2(10, array);

        minimumBribes(new int[] {1, 2, 5, 3, 7, 8, 6, 4});
        System.out.println(sum(1234));
    }

    static String containsSubString(String s1, String s2) {
        int [] s1Arr = new int[27];
        int offset = 97;
        char[] s1Chars = s1.toCharArray();
        for(int i = 0; i < s1Chars.length - 1; i++) {
            s1Arr[s1Chars[i] - offset] = 1;
        }

        for(int i = 0; i < s2.length() - 1; i++) {
            if (s1Arr[(int)s2.charAt(i) - offset] == 1) {
                return "YES";
            }
        }

        return "NO";
    }

    static void minimumBribes(int[] arr) {
        int count = 0;
        int chaoticCount = 0;
        for (int i = 0; i < arr.length -1; i++) {
            int swapLoc = i;
            while(arr[i] != i+1) {
                if (((arr[i] - 1) - swapLoc) > 2) {
                    System.out.println("Too chaotic");
                    chaoticCount++;
                    break;
                }

                int temp = arr[i];
                swapLoc = temp - 1;
                arr[i] = arr [temp - 1];
                arr [temp - 1] = temp;
                count ++;
            }

            if(chaoticCount > 0){
                break;
            }
        }

        if(chaoticCount == 0){
            System.out.println(count);
        }

    }

    static long arrayManipulation2(int n, int[][] queries) {
        int max = 0;
        int start = 0;
        int end = n;
        int startCount = 0;
        int endCount = 0;
        for (int j = 0; j < queries.length; j++) {
            if(queries[j][0] > end || queries[j][1] < start) {
                continue;
            } else {
                if (queries[j][0] >= start) {
                    start = queries[j][0];
                    startCount = startCount + queries[j][2];
                }

                if (queries[j][1] <= end) {
                    end = queries[j][1];
                    endCount = endCount + queries[j][2];
                }

                if (queries[j][0] < start && queries[j][1] > end) {
                    startCount = startCount + queries[j][2];
                    endCount = endCount + queries[j][2];
                }
            }


            if (startCount > endCount) {
                max = startCount;
            } else {
                max = endCount;
            }

        }

        return max;
    }

    static long arrayManipulation1(int n, int[][] queries) {
        int max = 0;
        int[] array = new int[n];
        for (int j = 0; j < queries.length; j++) {
            int start = queries[j][0];
            int end = queries[j][1];
            for (int k = start; k <= end && k < n; k++) {
                array[k] = array[k] + queries[j][2];
                if (max < array[k]) {
                    max = array[k];
                }
            }
        }

        System.out.println(max);

        return max;
    }

    //for this to work use long instead of int
    // and remove recursive call from sum method.
    // super number is the sum of digits of a number un till it is single digit.
    static int superDigit(String n, int k) {
        int digit = BigInteger.valueOf(Long.getLong(n)).intValue();
        int[] sum = new int[n.length() + 1];
        for (int i = 0; i < digit; i++) {
            if (i < 10) {
                sum[i] = i;
            } else {
                sum[i] = sum[sum(i)];
            }
        }

        int sum_n = k*sum[digit];
        while (sum_n > 9) {
            sum_n = sum(sum_n);
        }

        return sum_n;
    }

    private static int sum(int num) {
        int sum = 0;
        if (num <= 9) {
            return num;
        }

        int lastDigit = num%10;
        while(num > 0) {
            sum += lastDigit;
            num = num/10;
            lastDigit = num%10;
        }

        if (sum > 9) {
            sum = sum(sum);
        }

        return sum;
    }
}
