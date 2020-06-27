package com.practice;

import java.util.Arrays;
import java.util.Comparator;

/*
* You have a duffel bag to steal cakes from a shop.
* The weight of cake and its value is given as an array .For example: [[2, 15], [3, 90], [7, 200]].
* The duffel bag has a definite weight capacity to carry .For example:  20.
* Write a function to find accepting array and weight to provide the best possible combination of
* cakes stolen to get maximum profit.
*
* Sol:
*         0  1  2  3  4   5   6   7   8
* 2 (25)  0  0 25 25 50  50  75  75 100
* 3 (90)  0  0 25 90 90 115 180 180 205
* 7 (200) 0  0 25 90 90 115 180 200 205
*
* Here the order of the array should be ascending based on the weight.
* */
public class StealingCakeProblem {

    public static void main(String[] args) {
        int maxProfit = maxProfit(new int[][] {new int[] {3, 90}, new int[] {2, 25}, new int[] {7, 200}}, 8);
        System.out.println(maxProfit);
    }

    public static int maxProfit(int[][] array, int capacity) {
        int[][] maxProfitArray = new int[array.length][capacity + 1];

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] array1, int[] array2) {
                return array1[0] - array2[0];
            }
        });

        int minWeight = array[0][0];
        for (int index = 0; index < array.length; index++) {
            int currentWeight = array[index][0];
            int currentValue = array[index][1];
            for (int currentCapacity = 1; currentCapacity <= capacity; currentCapacity++) {
                if (currentCapacity < minWeight) {
                    maxProfitArray[index][currentCapacity] = 0;
                    continue;
                }

                if (currentCapacity >= currentWeight) {
                    int r = currentCapacity/currentWeight;
                    int currMaxProfit = 0;
                    if (index == 0) {
                        while(r > 0) {
                            int currProfit  = currentValue * r;
                            if (currProfit > currMaxProfit) {
                                currMaxProfit = currProfit;
                            }

                            r--;
                        }

                        maxProfitArray[index][currentCapacity] = currMaxProfit;
                    } else {
                        while(r > 0) {
                            int currProfit  = (currentValue * r)
                                    + maxProfitArray[index - 1][currentCapacity - (r * currentWeight)];
                            if (currProfit > currMaxProfit) {
                                currMaxProfit = currProfit;
                            }

                            r--;
                        }

                        maxProfitArray[index][currentCapacity] = Math.max(currMaxProfit, maxProfitArray[index - 1][currentCapacity]);
                    }
                } else {
                    maxProfitArray[index][currentCapacity] = maxProfitArray[index - 1][currentCapacity];
                }
            }
        }

        return maxProfitArray[array.length -1][capacity];
    }
}
