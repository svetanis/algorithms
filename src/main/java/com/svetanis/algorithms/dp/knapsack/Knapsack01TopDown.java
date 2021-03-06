package com.svetanis.algorithms.dp.knapsack;

import static com.svetanis.algorithms.dp.knapsack.Knapsack01SpaceOptimized.build;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

// Given two integer arrays to represent weights and profits of ‘N’ items, 
// we need to find a subset of these items which will give us maximum profit 
// such that their cumulative weight is not more than a given number ‘C’. 
// Each item can only be selected once, which means either 
// we put an item in the knapsack or we skip it.

public final class Knapsack01TopDown {

  // Time Complexity: O(n*W)
  // Space Complexity: O(n*W)

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
      int incl = items[n].value + knapsack(items, max - items[n].weight, n - 1, dp);
      int excl = knapsack(items, max, n - 1, dp);
      dp[n][max] = max(incl, excl);
    }
    return dp[n][max];
  }

  public static void main(String[] args) {
    int max = 10;
    int[] weight = { 1, 2, 3, 8, 7, 4 };
    int[] value = { 20, 5, 10, 40, 15, 25 };
    System.out.println(knapsack(weight, value, max));
  }
}
