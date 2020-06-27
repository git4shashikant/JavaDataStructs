package com.practice;

/*
* Determine
* 1 1 0 1 0
* 0 1 1 1 0
* 1 1 1 1 0
* 0 1 1 1 1
*
* Solution: update cell containing 1, except i = 0 and j = 0, with max square value by adding
* minimum of adjacent left side surrounding cells.
*
* if array[i][j] == 1; array[i][j] = 1 + Math.min(array[i-1][j], array[i-1][j-1], array[i][j-1]);
* While calculating these values, keep track of the max value and that would be the answer.
*
* So, the resulting matrix would be:
* 1 1 0 1 0
* 0 1 1 1 0
* 1 1 2 2 0
* 0 1 2 3 1
* */
public class MaxSquareInMatrix {

    public static void main(String[] args) {
        System.out.println(maxSquareInMatrix(new int[][]{
                new int[]{1, 1, 0, 1, 0},
                new int[]{0, 1, 1, 1, 0},
                new int[]{1, 1, 1, 1, 0},
                new int[]{0, 1, 1, 1, 1}
        }));
    }

    public static int maxSquareInMatrix(int[][] matrix) {
        int maxSquare = 0;
        for (int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if (i != 0 && j != 0 && matrix[i][j] == 1) {
                    matrix[i][j] = matrix[i][j] + Math.min(Math.min(matrix[i][j-1], matrix[i-1][j-1]), matrix[i-1][j]);
                    if (matrix[i][j] > maxSquare) {
                        maxSquare = matrix[i][j];
                    }
                }
            }
        }

        return maxSquare;
    }
}
