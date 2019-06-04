package com.svetanis.algorithms.dp.coins;

public final class CoinChangeDPSpaceOptimized {

//Given a value N, if we want to make change for N cents, 
//and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
//how many ways can we make the change? The order of coins doesn’t matter.

  // n - size of array of coins a
  // v - coin value
  // dp[i] will be storing the number
  // of solutions for value i.
  // we need n + 1 rows as the table is
  // constructed in bottom up manner
  // using the base case (n = 0)

  public static int change(int[] a, int max) {

    int n = a.length;
    int[] dp = new int[max + 1];

    // base case
    // given value is 0
    dp[0] = 1;

    // pick all coins one by one and
    // update the dp[] values
    // after the index greater than
    // or equal to the value of
    // the picked coin
    for (int i = 0; i < n; ++i) {
      for (int v = a[i]; v <= max; v++) {
        dp[v] = (dp[v] + dp[v - a[i]]) % 1000007;
      }
    }
    return dp[max] % 1000007;
  }

  public static void main(String[] args) {
    int v = 4;
    int[] coins = { 1, 2, 3 };
    System.out.println(change(coins, v));
  }
}
