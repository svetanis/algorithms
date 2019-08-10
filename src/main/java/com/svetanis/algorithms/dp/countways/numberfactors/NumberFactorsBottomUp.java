package com.svetanis.algorithms.dp.countways.numberfactors;

public final class NumberFactorsBottomUp {

  // Given a number ‘n’, implement a method
  // to count how many possible ways there are
  // to express ‘n’ as the sum of 1, 3, or 4.

  public static int count(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 1;
    dp[3] = 2;
    for (int i = 4; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];
    }
    return dp[n];
  }

  public static void main(String[] args) {
    System.out.println(count(4));
    System.out.println(count(5));
    System.out.println(count(6));
  }
}
