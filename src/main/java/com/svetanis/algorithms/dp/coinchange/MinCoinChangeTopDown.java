package com.svetanis.algorithms.dp.coinchange;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

public final class MinCoinChangeTopDown {

  // Given a value V, if we want to make change for V cents,
  // and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
  // what is the minimum number of coins to make the change?

  // n - size of array of coins S
  // V - coin value

  public static int minCoins(int[] a, int v) {
    int[] dp = new int[v + 1];
    return minCoins(a, v, dp, 0);
  }

  public static int minCoins(int[] a, int v, int[] dp, int j) {
    int n = a.length;

    if (v == 0) {
      return 0;
    }

    if (v < 0) {
      return MAX_VALUE;
    }

    if (dp[j] == v) {
      return dp[j];
    }

    int min = MAX_VALUE;
    for (int i = 0; i < n; i++) {
      int coins = minCoins(a, v - a[i], dp, j + 1);
      if (coins != MAX_VALUE) {
        min = min(min, coins + 1);
      }
    }
    dp[j] = min;
    return min;
  }

  public static void main(String[] args) {
    int[] a1 = { 25, 10, 5 };
    System.out.println(minCoins(a1, 30));

    int[] a2 = { 9, 6, 5, 1 };
    System.out.println(minCoins(a2, 11));
  }
}
