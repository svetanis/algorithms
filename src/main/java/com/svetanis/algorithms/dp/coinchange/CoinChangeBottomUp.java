package com.svetanis.algorithms.dp.coinchange;

import static java.util.Arrays.fill;

public final class CoinChangeBottomUp {

  // n - size of array of coins S
  // V - coin value
  // we need n + 1 rows as the table is
  // constructed in bottom up manner
  // using the base case 0 value case (n = 0)
  public static int change(int[] a, int max) {

    int n = a.length;
    int[][] dp = new int[max + 1][n];
    // fill the entries for 0 value case
    fill(dp[0], 1);

    for (int v = 1; v <= max; ++v) {
      for (int i = 0; i < n; ++i) {
        int excl = 0;
        if (i > 0) {
          excl = dp[v][i - 1];
        }

        int incl = 0;
        if (v >= a[i]) {
          incl = dp[v - a[i]][i];
        }

        // total count
        dp[v][i] = incl + excl;
      }
    }
    return dp[max][n - 1];
  }

  public static void main(String[] args) {
    int[] coins = { 1, 2, 3 };
    System.out.println(change(coins, 4));
  }
}
