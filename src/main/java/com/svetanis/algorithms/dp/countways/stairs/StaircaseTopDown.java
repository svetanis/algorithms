package com.svetanis.algorithms.dp.countways.stairs;

import static java.util.Arrays.fill;

// Given a stair with ‘n’ steps,
// implement a method to count
// how many possible ways are there
// to reach the top of the staircase,
// given that, at every step you can
// either take 1 step, 2 steps, or 3 steps.

public final class StaircaseTopDown {

  public static int count(int n) {
    int[] dp = new int[n + 1];
    fill(dp, -1);
    return count(n, dp);
  }

  private static int count(int n, int[] dp) {
    if (n < 0) {
      return 0;
    }

    if (n == 0) {
      return 1;
    }

    if (n == 2) {
      return 2;
    }

    if (dp[n] > -1) {
      return dp[n];
    }

    int one = count(n - 1, dp);
    int two = count(n - 2, dp);
    int three = count(n - 3, dp);
    dp[n] = one + two + three;
    return dp[n];
  }

  public static void main(String[] args) {
    System.out.println(count(3));
  }
}
