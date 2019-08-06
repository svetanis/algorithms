package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of non-negative integers, and a value sum, 
//determine if there is a subset of the given set with sum equal to given sum.

public final class SubSetSumTopDown {

  public static boolean isSum(int[] a, int sum) {
    int n = a.length;
    Boolean[][] dp = new Boolean[n + 1][sum + 1];
    return isSum(a, 0, sum, dp);
  }

  private static boolean isSum(int[] a, int i, int sum, Boolean[][] dp) {
    // Time complexity: O(n * sum)

    int n = a.length;

    if (sum == 0) {
      return true;
    }

    if (i >= n || sum < 0) {
      return false;
    }

    if (dp[i][sum] == null) {

      // 1. include a[i]
      boolean incl = isSum(a, i + 1, sum - a[i], dp);

      // 2. exclude a[i]
      boolean excl = isSum(a, i + 1, sum, dp);

      dp[i][sum] = incl || excl;
    }
    return dp[i][sum];
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
