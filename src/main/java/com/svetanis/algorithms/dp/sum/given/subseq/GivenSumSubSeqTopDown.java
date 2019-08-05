package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of non-negative integers, and a value sum, 
//determine if there is a subset of the given set with sum equal to given sum.

public final class GivenSumSubSeqTopDown {

  public static boolean isSum(int[] a, int sum) {
    int n = a.length;
    int[][] dp = init(n, sum);
    return isSum(a, 0, sum, dp) == 1 ? true : false;
  }

  private static int isSum(int[] a, int i, int sum, int[][] dp) {
    // Time complexity: O(n * sum)

    int n = a.length;

    if (sum == 0) {
      return 1;
    }

    if (n == 0 && sum != 0) {
      return 0;
    }

    if (a[i] > sum) {
      return isSum(a, i + 1, sum, dp);
    }

    if (dp[i][sum] != -1) {
      return dp[i][sum];
    }

    // 1. include a[i]
    int result = isSum(a, i + 1, sum - a[i], dp);
    if (result != -1) {
      dp[i + 1][sum - a[i]] = result;
      return result;
    }

    // 2. exclude a[i]
    result = isSum(a, i + 1, sum, dp);
    if (result != -1) {
      dp[i + 1][sum] = result;
      return result;
    }

    return result;
  }

  private static int[][] init(int n, int k) {
    int[][] dp = new int[n + 1][k + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= k; j++) {
        dp[i][j] = -1;
      }
    }
    return dp;
  }

  public static void main(String[] args) {
    int[] a = { 3, 34, 4, 12, 5, 2 };
    System.out.println(isSum(a, 9));
  }
}
