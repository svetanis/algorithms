package com.svetanis.algorithms.dp.countways.robbery;

import static java.lang.Math.max;

public final class Robbery {

  public static int maxProfit(int[] a) {
    int n = a.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      int excl = i > 0 ? dp[i - 1] : 0;
      int incl = i > 1 ? dp[i - 2] + a[i] : a[i];
      dp[i] = max(excl, incl);
    }
    return dp[n - 1];
  }

  public static void main(String[] args) {
    int[] a = { 6, 1, 2, 7 };
    System.out.println(maxProfit(a));
  }
}
