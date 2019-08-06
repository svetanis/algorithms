package com.svetanis.algorithms.dp.knapsack;

import static com.svetanis.algorithms.dp.knapsack.Knapsack01SpaceOptimized.build;
import static java.lang.Math.max;

// Given two integer arrays to represent weights and profits of ‘N’ items, 
// we need to find a subset of these items which will give us maximum profit 
// such that their cumulative weight is not more than a given number ‘C’. 
// We can assume an infinite supply of item quantities; 
// therefore, each item can be selected multiple times.

public final class UnboundedKnapsackBottomUp {

  // Time Complexity: O(n*W)
  // Space Complexity: O(n*W)

  public static int knapsack(int[] w, int[] v, int max) {
    int n = w.length;
    int m = v.length;
    if (max <= 0 || n == 0 || n != m) {
      return 0;
    }
    Item[] items = build(v, w);
    return knapsack(items, max, n);
  }

  private static int knapsack(Item[] items, int max, int n) {
    int[][] dp = new int[n + 1][max + 1];
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= max; ++j) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (items[i - 1].weight <= j) {
          int excl = dp[i - 1][j];
          int incl = items[i - 1].value + dp[i][j - items[i - 1].weight];
          dp[i][j] = max(incl, excl);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
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
