package com.practice;

import java.util.Arrays;

/*
* Below is the matrix of prices of a share.
* We have to calculate the maximum profit earned in t number of transactions
* price array:    [3, 10, 5, 22, 3, 85]
*
* For maximum profit, one can do either of the below 2 things and check whats the maximum value:
* 1. No transaction today, so the profit = profit(t, d-1)
* 2. Sell stock: In this scenario, if user sells his stock then he must have bought it before on a day
*    when the prices are low to get max profit from the current transaction and also at the same time
*    the profit earned till that time is maximum. assume if that instance is x.
*    So, total profit: profit from current transaction + profit from previous transaction.
*                    = [price(d) - price(x)]           + [profit(t-1, x)]
*    Now, as we don't know that instance, so we need to find the Max(profit(t-1, x) - price(x))
*
* Combining both the above cases, derived formula would look like below:
*
* Profit(t, d) = Max[Profit(t, d-1), Price(d) + Max(profit(t-1, x) - price(x))]
* where: t is the nth transaction, d is the day, x is any day before d.
*
*
* */
public class MaxProfit {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {3, 10, 5, 22, 3, 85}, 3));
        System.out.println(maxProfitRecursion(new int[] {3, 10, 5, 22, 3, 85}, 3));
    }

    static int maxProfit(int[] prices, int transactions) {
        int[][] profit = new int[transactions][prices.length];
        for (int txn = 0; txn < transactions; txn++) {
            for (int day = 0; day < prices.length; day++) {
                if (day == 0) {
                    profit[txn][day] = 0;
                    continue;
                }

                if (txn == 0) {
                    int minBuyValue = prices[day - 1];
                    for (int buyDay = day-2; buyDay >= 0; buyDay--) {
                        if (prices[buyDay] < minBuyValue) {
                            minBuyValue = prices[buyDay];
                        }
                    }

                    profit[txn][day] = Math.max(profit[txn][day-1], prices[day] - minBuyValue);
                } else {
                    int maxValue = 0;
                    for (int buyDay = day-1; buyDay >= 0; buyDay--) {
                        if (profit[txn-1][buyDay] - prices[buyDay] > maxValue) {
                            maxValue = profit[txn-1][buyDay] - prices[buyDay];
                        }
                    }

                    profit[txn][day] = Math.max(profit[txn][day-1] ,prices[day] + maxValue);
                }
            }
        }

        return profit[transactions - 1][prices.length - 1];
    }

    static int maxProfitRecursion(int[] prices, int transactions) {
        if (prices.length == 1 || transactions == 0) {
            return 0;
        }

        int today = prices.length - 1;

        int previousDayProfit = maxProfitRecursion(Arrays.copyOfRange(prices, 0, today), transactions);
        if (transactions == 1) {
            int minBuyValue = prices[today - 1];
            int buyDayIndex  = today - 2;
            while(buyDayIndex >= 0) {
                if (prices[buyDayIndex] < minBuyValue) {
                    minBuyValue = prices[buyDayIndex];
                }

                buyDayIndex--;
            }

            return Math.max(previousDayProfit, prices[today] - minBuyValue);
        }

        int maxProfitSellToday = 0;
        for (int buyDay = today - 1; buyDay >= 0; buyDay--) {
            int buyDayProfitFromPrevTxn = maxProfitRecursion(Arrays.copyOfRange(prices, 0, buyDay + 1), transactions - 1);
            int maxPossibleProfit = buyDayProfitFromPrevTxn - prices[buyDay];
            if (maxPossibleProfit > maxProfitSellToday) {
                maxProfitSellToday = maxPossibleProfit;
            }
        }

        return Math.max(previousDayProfit, prices[today] + maxProfitSellToday);
    }
}
