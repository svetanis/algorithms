package com.svetanis.algorithms.dp.countways.robbery;

import static java.lang.Math.max;

// Given a number array representing 
// the wealth of ‘n’ houses, determine 
// the maximum amount of money the thief 
// can steal without alerting the security system.

public final class HouseThiefTopDown {

  public static int maxProfit(int[] a) {
    int[] dp = new int[a.length];
    return maxProfit(a, dp, 0);
  }

  private static int maxProfit(int[] a, int[] dp, int index) {
    if (index >= a.length) {
      return 0;
    }

    if (dp[index] != 0) {
      return dp[index];
    }

    // include
    int incl = a[index] + maxProfit(a, dp, index + 2);
    // exclude
    int excl = maxProfit(a, dp, index + 1);
    dp[index] = max(incl, excl);
    return dp[index];
  }

  public static void main(String[] args) {
    int[] a1 = { 2, 5, 1, 3, 6, 2, 4 };
    System.out.println(maxProfit(a1));

    int[] a2 = { 2, 10, 14, 8, 1 };
    System.out.println(maxProfit(a2));

  }
}
