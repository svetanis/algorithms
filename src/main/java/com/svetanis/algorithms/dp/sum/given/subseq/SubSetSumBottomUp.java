package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of non-negative integers, and a value sum, 
//determine if there is a subset of the given set with sum equal to given sum.

public final class SubSetSumBottomUp {

  public static boolean isSum(int[] a, int sum) {
    // Time complexity: O(sum * n)

    int n = a.length;

    // the value of subset[i][j] will be true
    // if there is a subset of set[0 ... j - 1]
    // with sum equal to i
    boolean[][] dp = new boolean[sum + 1][n + 1];

    // if sum is 0, then answer is true
    for (int i = 0; i <= n; ++i) {
      dp[0][i] = true;
    }

    // if sum is not 0 and set is empty,
    // then answer is false
    for (int s = 1; s <= sum; s++) {
      dp[s][0] = false;
    }

    // fill the subset table in bottom up manner
    for (int s = 1; s <= sum; ++s) {
      for (int j = 1; j <= n; ++j) {
        dp[s][j] = dp[s][j - 1];

        if (s >= a[j - 1]) {
          boolean incl = dp[s - a[j - 1]][j - 1];
          boolean excl = dp[s][j - 1];
          dp[s][j] = incl || excl;
        }
      }
    }
    return dp[sum][n];
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
