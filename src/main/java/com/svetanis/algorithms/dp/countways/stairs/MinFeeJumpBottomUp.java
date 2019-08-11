package com.svetanis.algorithms.dp.countways.stairs;

import static java.lang.Math.min;

public final class MinFeeJumpBottomUp {

  // Given a staircase with ‘n’ steps and
  // an array of ‘n’ numbers representing the fee
  // that you have to pay if you take the step.
  // Implement a method to calculate the min fee
  // required to reach the top of the staircase.
  // At every step, you have an option to take
  // either 1 step, 2 steps, or 3 steps.
  // You should assume that you are standing at the first step.

  public static int count(int[] a) {
    int n = a.length;
    int[] dp = new int[a.length + 1];
    dp[0] = 0;
    dp[1] = a[0];
    dp[2] = dp[3] = a[0];
    for (int i = 3; i < n; i++) {
      int min = min(dp[i - 1] + a[i - 1], dp[i - 2] + a[i - 2]);
      dp[i + 1] = min(dp[i] + a[i], min);
    }
    return dp[n];
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 5, 2, 1, 2 };
    System.out.println(count(a1));

    int[] a2 = { 2, 3, 4, 5 };
    System.out.println(count(a2));

  }
}
