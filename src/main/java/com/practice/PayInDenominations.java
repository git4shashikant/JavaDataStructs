package com.practice;

import java.util.Arrays;

/*
* Find number of ways a payment can be done using an array of provided denominations.
*
* This is an example of dynamic programming, this can also be solved by recursion but will do recalculations in stack.
* Logic:
* If number < denominator:
* Fetch ways of paying the amount by previous denominator (this value must be previously
* calculated while iterating through the denominations possibilities.)
* If number >= denominator:
* number/denominator = r (possibilities of paying by highest denominations, which could be 1*d + remaining amount or
* 2*d + remaining amount or r*d + remaining amount etc.), so then the ways will only be dependent on how we can pay the
* remaining amount.
* So, for each iteration ways = ways[remaining amount for previous denomination]
*
* Below is the 2 D array showing the combinations:
*          Amounts -->
*       D  0 1 2 3 4 5 6
*       1  1 1 1 1 1 1 1
*       3  1 1 1 2 2 2 3
*       5  1 1 1 2 2 3 4
*
* In the below solution the order of array should be ascending.
* */
public class PayInDenominations {

    public static void main(String[] args) {
        System.out.println(waysToPay(6, new int[]{1, 3, 5}));
        System.out.println(payWithMinCoinsRecursion(8, new int[]{1, 3, 5}));
    }

    public static int waysToPay(int payment, int[] denominations) {
        int [][] defaultWays = new int[denominations.length][payment+1];
        int count = 0;

        for (int denominatorIndex = 0; denominatorIndex < denominations.length; denominatorIndex++) {
            for (int number = 0; number <= payment; number++) {
                int denominator = denominations[denominatorIndex];
                if (denominator == 1) {
                    defaultWays[denominatorIndex][number] = 1;
                    continue;
                }

                if (number >= denominator) {
                    int r = number/denominator;
                    while (r >= 0) {
                        count += defaultWays[denominatorIndex - 1][number - (r*denominator)];
                        r--;
                    }

                    defaultWays[denominatorIndex][number] = count;
                    count = 0;
                } else {
                    defaultWays[denominatorIndex][number] = defaultWays[denominatorIndex - 1][number];
                }
            }
        }

        return defaultWays[denominations.length-1][payment];
    }

    public static int payWithMinCoinsRecursion(int payment, int[] denominations) {
        int minCoins;
        if (denominations.length == 0) {
            return 0;
        }

        int highestDenomination = denominations[denominations.length -1];
        if (payment < highestDenomination) {
            minCoins = payWithMinCoinsRecursion(payment, Arrays.copyOfRange(denominations, 0, denominations.length - 1));
        } else {
            if (highestDenomination == 1) {
                minCoins = payment;
            } else {
                int currMinCount = 100;
                int r = payment/highestDenomination;
                while(r > 0) {
                    int currCount = r + payWithMinCoinsRecursion(payment - (r * highestDenomination),
                            Arrays.copyOfRange(denominations, 0, denominations.length - 1));
                    if (currCount < currMinCount) {
                        currMinCount = currCount;
                    }

                    r--;
                }

                minCoins = currMinCount;
            }
        }

        return minCoins;
    }

}