package com.svetanis.algorithms.dp.coinchange;

public final class CoinChangeDynamic {

  // n - size of array of coins S
  // V - coin value
  // we need n + 1 rows as the table is
  // constructed in bottom up manner
  // using the base case 0 value case (n = 0)
  public static int change(int[] s, int max) {

    int incl, excl;
    int n = s.length;
    int[][] dp = new int[max + 1][n];
    // fill the entries for 0 value case
    for (int i = 0; i < n; ++i) {
      dp[0][i] = 1;
    }

    // fill rest of the table entries
    // in bottom up manner
    for (int v = 1; v < max + 1; ++v) {
      for (int i = 0; i < n; ++i) {
        // count of solutions including S[j]
        if (v - s[i] >= 0) {
          incl = dp[v - s[i]][i];
        } else {
          incl = 0;
        }

        // count of solutions excluding S[j]
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
    int v = 4;
    int[] coins = { 1, 2, 3 };
    System.out.println(change(coins, v));
  }
}
