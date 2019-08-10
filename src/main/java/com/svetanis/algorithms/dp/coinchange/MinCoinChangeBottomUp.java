package com.svetanis.algorithms.dp.coinchange;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.fill;

public final class MinCoinChangeBottomUp {

  // Given a value V, if we want to make change for V cents,
  // and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
  // what is the minimum number of coins to make the change?

  // n - size of array of coins S
  // V - coin value

  public static int minCoins(int[] a, int max) {
    int n = a.length;
    int[] dp = new int[max + 1];
    fill(dp, MAX_VALUE);
    dp[0] = 0;
    for (int v = 1; v <= max; v++) {
      int coins = MAX_VALUE;
      for (int j = 0; j < n; j++) {
        if (a[j] <= v) {
          coins = dp[v - a[j]];
        }
        if (coins != MAX_VALUE) {
          dp[v] = min(dp[v], coins + 1);
        }
      }
    }
    return dp[max];
  }

  public static void main(String[] args) {
    int[] a1 = { 25, 10, 5 };
    System.out.println(minCoins(a1, 30));

    int[] a2 = { 9, 6, 5, 1 };
    System.out.println(minCoins(a2, 11));
  }
}
