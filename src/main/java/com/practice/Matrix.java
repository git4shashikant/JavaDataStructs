package com.practice;

import java.math.BigInteger;

/*
*   0 1 2 3 4 5
* 0 1
* 1 1 1
* 2 1 2 1
* 3 1 3 3 1
* 4 1 4 6 4 1
*
* Given above pattern, if l represent row index and c represent column index, then find the value of element
* at (l,c)
* */
public class Matrix {

    public static void main(String []args){

        try {
            System.out.println(calculateUsingRecursion(4, 0));
            System.out.println(calculateUsingRecursion(4, 4));
            System.out.println(calculateUsingRecursion(4, 2));
            System.out.println(calculateUsingRecursion(5, 3));
            System.out.println(calculateUsingRecursion(4, 5));
            System.out.println(calculateUsingDP(67, 34));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static int calculateUsingRecursion(int l, int c) throws Exception {
        if (c == 0 || c == l)
            return 1;

        if (c > l)
            throw new Exception("Not a number");

        return calculateUsingRecursion(l-1, c-1) + calculateUsingRecursion(l-1, c);
    }

    public static String calculateUsingDP(int l, int c) throws Exception {

        if (c == 0 || c == l)
            return BigInteger.valueOf(1).toString();

        if (c > l)
            throw new Exception("Not a valid number");

        BigInteger [][] arr = new BigInteger[l+1][l+1];
        int i=0;
        int j=0;
        while (i <= l) {
            while(j <= i) {
                if (i==j || j == 0) {
                    arr[i][j] = BigInteger.valueOf(1);
                } else {
                    arr[i][j] = arr[i - 1][j - 1].add(arr[i - 1][j]);
                }

                j++;
            }

            j=0;
            i++;
        }

        return arr[l][c].toString();
    }
}
