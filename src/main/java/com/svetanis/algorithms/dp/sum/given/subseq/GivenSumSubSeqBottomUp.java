package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of non-negative integers, and a value sum, 
//determine if there is a subset of the given set with sum equal to given sum.

public final class GivenSumSubSeqBottomUp {

  public static boolean isSum(int[] a, int k) {
    // Time complexity: O(sum * n)

    int n = a.length;

    // the value of subset[i][j] will be true
    // if there is a subset of set[0 ... j - 1]
    // with sum equal to i
    boolean[][] dp = new boolean[k + 1][n + 1];

    // if sum is 0, then answer is true
    for (int i = 0; i <= n; ++i) {
      dp[0][i] = true;
    }

    // if sum is not 0 and set is empty,
    // then answer is false
    for (int sum = 1; sum <= k; ++sum) {
      dp[sum][0] = false;
    }

    // fill the subset table in bottom up manner
    for (int sum = 1; sum <= k; ++sum) {
      for (int j = 1; j <= n; ++j) {
        dp[sum][j] = dp[sum][j - 1];

        if (sum >= a[j - 1]) {
          boolean incl = dp[sum - a[j - 1]][j - 1];
          boolean excl = dp[sum][j - 1];
          dp[sum][j] = incl || excl;
        }
      }
    }
    return dp[k][n];
  }

  public static void main(String[] args) {
    int[] a = { 3, 34, 4, 12, 5, 2 };
    System.out.println(isSum(a, 9));
  }
}
