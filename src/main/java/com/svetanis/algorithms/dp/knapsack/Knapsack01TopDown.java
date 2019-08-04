package com.svetanis.algorithms.dp.knapsack;

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
    Integer[][] dp = new Integer[v.length][max + 1];
    return knapsack(w, v, max, v.length, dp);
  }

  private static int knapsack(int[] w, int[] v, int max, int n, Integer[][] dp) {
    // base case
    if (n == 1 || max <= 0) {
      return 0;
    }

    if (dp[n - 1][max] != null) {
      return dp[n - 1][max];
    }

    int incl = 0;
    if (w[n - 1] <= max) {
      incl = knapsack(w, v, max - w[n - 1], n - 1, dp) + v[n - 1];
    }
    int excl = knapsack(w, v, max, n - 1, dp);
    dp[n - 1][max] = max(incl, excl);
    return dp[n - 1][max];
  }

  public static void main(String[] args) {
    int max = 50;
    int[] weight = { 10, 20, 30 };
    int[] value = { 60, 100, 120 };
    System.out.println(knapsack(weight, value, max));
  }
}
