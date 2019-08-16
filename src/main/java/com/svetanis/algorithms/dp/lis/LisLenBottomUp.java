package com.svetanis.algorithms.dp.lis;

import static java.lang.Math.max;
import static java.util.Arrays.fill;

public final class LisLenBottomUp {

  public static int lis(int[] a) {
    // Time Complexity: O(n^2)

    int n = a.length;
    int[] dp = new int[n];
    fill(dp, 1);

    int max = 1;
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i]) {
          dp[i] = max(dp[i], dp[j] + 1);
          max = max(max, dp[i]);
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
    System.out.println(lis(a));
  }
}