package com.svetanis.algorithms.dp.knapsack;

import static com.svetanis.algorithms.dp.knapsack.Knapsack01SpaceOptimized.build;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

// Given two integer arrays to represent weights and profits of ‘N’ items, 
// we need to find a subset of these items which will give us maximum profit 
// such that their cumulative weight is not more than a given number ‘C’. 
// We can assume an infinite supply of item quantities; 
// therefore, each item can be selected multiple times.

public final class UnboundedKnapsackTopDown {

  // Time Complexity: O(n*W)
  // Space Complexity: O(n*W + n)

  public static int knapsack(int[] w, int[] v, int max) {
    int n = w.length;
    int m = v.length;
    if (max <= 0 || n == 0 || n != m) {
      return 0;
    }
    Item[] items = build(v, w);
    Integer[][] dp = new Integer[n][max + 1];
    return knapsack(items, max, n - 1, dp);
  }

  private static int knapsack(Item[] items, int max, int n, Integer[][] dp) {
    // base case: negative capacity
    if (max < 0) {
      return MIN_VALUE;
    }

    // base case: no items left or capacity becomes 0
    if (n < 0 || max == 0) {
      return 0;
    }

    if (dp[n][max] == null) {
      int incl = items[n].value + knapsack(items, max - items[n].weight, n, dp);
      int excl = knapsack(items, max, n - 1, dp);
      dp[n][max] = max(incl, excl);
    }
    return dp[n][max];
  }

  public static void main(String[] args) {
    int max = 8;
    int[] weight = { 1, 3, 4, 5 };
    int[] value = { 15, 50, 60, 90 };
    System.out.println(knapsack(weight, value, max));
  }
}
