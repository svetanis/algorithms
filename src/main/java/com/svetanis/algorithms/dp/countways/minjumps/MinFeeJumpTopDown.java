package com.svetanis.algorithms.dp.countways.minjumps;

import static java.lang.Math.min;

public final class MinFeeJumpTopDown {

  // Given a staircase with ‘n’ steps and
  // an array of ‘n’ numbers representing the fee
  // that you have to pay if you take the step.
  // Implement a method to calculate the min fee
  // required to reach the top of the staircase.
  // At every step, you have an option to take
  // either 1 step, 2 steps, or 3 steps.
  // You should assume that you are standing at the first step.

  public static int count(int[] a) {
    int[] dp = new int[a.length];
    return count(a, dp, 0);
  }

  private static int count(int[] a, int[] dp, int index) {
    int n = a.length;
    // base case
    if (index > n - 1) {
      return 0;
    }

    if (dp[index] != 0) {
      return dp[index];
    }

    int one = count(a, dp, index + 1);
    int two = count(a, dp, index + 2);
    int three = count(a, dp, index + 3);
    int min = min(one, min(two, three));
    dp[index] = a[index] + min;
    return dp[index];
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 5, 2, 1, 2 };
    System.out.println(count(a1));

    int[] a2 = { 2, 3, 4, 5 };
    System.out.println(count(a2));

  }
}
