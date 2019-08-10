package com.svetanis.algorithms.dp.countways.numberfactors;

import static java.util.Arrays.fill;

// Given a number ‘n’, implement a method
// to count how many possible ways there are
// to express ‘n’ as the sum of 1, 3, or 4.

public final class NumberFactorsTopDown {

  public static int count(int n) {
    int[] dp = new int[n + 1];
    fill(dp, -1);
    return count(n, dp);
  }

  private static int count(int n, int[] dp) {
    // base case
    if (n < 3) {
      return 1;
    }

    if (n == 3) {
      return 2;
    }

    if (dp[n] > -1) {
      return dp[n];
    }

    int one = count(n - 1, dp);
    int three = count(n - 3, dp);
    int four = count(n - 4, dp);
    dp[n] = one + three + four;
    return dp[n];
  }

  public static void main(String[] args) {
    System.out.println(count(4));
    System.out.println(count(5));
    System.out.println(count(6));
  }
}
