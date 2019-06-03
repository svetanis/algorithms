package com.svetanis.algorithms.dp.coins;

// n - size of array of coins a
// v - coin value
// we need n + 1 rows as the table is
// constructed in bottom up manner
// using the base case 0 value case (n = 0)

public final class CoinChangeDynamic {

  public static int change(int[] a, int max) {

    int n = a.length;
    int[][] dp = new int[max + 1][n];
    // fill the entries for 0 value case
    for (int i = 0; i < n; ++i) {
      dp[0][i] = 1;
    }

    // fill rest of the table entries
    // in bottom up manner
    for (int v = 1; v < max + 1; ++v) {
      int incl = 0;
      int excl = 0;  
      for (int i = 0; i < n; ++i) {
        // count of solutions including a[i]
        if (v - a[i] >= 0) {
          incl = dp[v - a[i]][i];
        } else {
          incl = 0;
        }

        // count of solutions excluding a[i]
        if (i >= 1) {
          excl = dp[v][i - 1];
        } else {
          excl = 0;
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
