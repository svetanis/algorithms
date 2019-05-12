package com.svetanis.algorithms.dp.knapsack;

import static java.lang.Math.max;

public final class Knapsack01Dynamic {

  public static int knapsack(int[] w, int[] v, int max, int n) {
    // Time Complexity: O(n*W)
    // Space Complexity: O(n*W)

    int[][] table = new int[n + 1][max + 1];

    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= max; ++j) {
        if (i == 0 || j == 0) {
          table[i][j] = 0;
        } else if (w[i - 1] <= j) {
          int excl = table[i - 1][j];
          int incl = table[i - 1][j - w[i - 1]] + v[i - 1];
          table[i][j] = max(incl, excl);
        } else {
          table[i][j] = table[i - 1][j];
        }
      }
    }
    return table[n][max];
  }

  public static void main(String[] args) {
    int max = 50;
    int[] value = { 60, 100, 120 };
    int[] weight = { 10, 20, 30 };
    System.out.println(knapsack(weight, value, max, value.length));
  }
}
