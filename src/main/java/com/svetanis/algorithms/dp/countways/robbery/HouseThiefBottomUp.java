package com.svetanis.algorithms.dp.countways.robbery;

import static java.lang.Math.max;

// Given a number array representing 
// the wealth of ‘n’ houses, determine 
// the maximum amount of money the thief 
// can steal without alerting the security system.

public final class HouseThiefBottomUp {

  public static int maxProfit(int[] a) {
    int n = a.length;
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = a[0];

    for (int i = 1; i < n; i++) {
      dp[i + 1] = max(dp[i - 1] + a[i], dp[i]);
    }
    return dp[n];
  }

  public static void main(String[] args) {
    int[] a1 = { 2, 5, 1, 3, 6, 2, 4 };
    System.out.println(maxProfit(a1));

    int[] a2 = { 2, 10, 14, 8, 1 };
    System.out.println(maxProfit(a2));

  }
}
