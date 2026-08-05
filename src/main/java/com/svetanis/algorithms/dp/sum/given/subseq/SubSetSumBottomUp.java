package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of non-negative integers, and a value sum,
//determine if there is a subset of the given set with sum equal to given sum.

// this is 0/1 knapsack with value == weight, so the table carries
// the same axes as dp/knapsack/Knapsack01BottomUp: dp[item][capacity].
// only the combine step differs -- max(incl, excl) becomes incl || excl.

public final class SubSetSumBottomUp {

  public static boolean isSum(int[] a, int sum) {
    // Time complexity: O(n * sum)

    int n = a.length;

    // the value of dp[i][s] will be true
    // if there is a subset of set[0 ... i - 1]
    // with sum equal to s
    boolean[][] dp = new boolean[n + 1][sum + 1];

    // if sum is 0, then answer is true
    for (int i = 0; i <= n; ++i) {
      dp[i][0] = true;
    }

    // if sum is not 0 and set is empty,
    // then answer is false
    for (int s = 1; s <= sum; ++s) {
      dp[0][s] = false;
    }

    // fill the subset table in bottom up manner
    // every read is from row i - 1, so the loops may be nested
    // either way round; row-major order is the one that collapses to 1-D
    for (int i = 1; i <= n; ++i) {
      for (int s = 1; s <= sum; ++s) {
        dp[i][s] = dp[i - 1][s];

        if (s >= a[i - 1]) {
          boolean incl = dp[i - 1][s - a[i - 1]];
          boolean excl = dp[i - 1][s];
          dp[i][s] = incl || excl;
        }
      }
    }
    return dp[n][sum];
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 34, 4, 12, 5, 2 };
    System.out.println(isSum(a1, 9));

    int[] a2 = { 1, 2, 3, 7 };
    System.out.println(isSum(a2, 6));

    int[] a3 = { 1, 2, 7, 1, 5 };
    System.out.println(isSum(a3, 10));

    int[] a4 = { 1, 3, 4, 8 };
    System.out.println(isSum(a4, 6));
  }
}
