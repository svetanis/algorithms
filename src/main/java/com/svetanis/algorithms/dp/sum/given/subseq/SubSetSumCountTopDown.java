package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of positive numbers, find the total number  
//of subsets whose sum is equal to a given number ‘S’.

public final class SubSetSumCountTopDown {

  public static int count(int[] a, int sum) {
    int n = a.length;
    Integer[][] dp = new Integer[n + 1][sum + 1];
    return count(a, 0, sum, dp);
  }

  private static int count(int[] a, int i, int sum, Integer[][] dp) {
    // Time complexity: O(n * sum)

    int n = a.length;

    if (sum == 0) {
      return 1;
    }

    if (i >= n || sum < 0) {
      return 0;
    }

    if (dp[i][sum] == null) {

      // 1. include a[i]
      int incl = count(a, i + 1, sum - a[i], dp);

      // 2. exclude a[i]
      int excl = count(a, i + 1, sum, dp);

      dp[i][sum] = incl + excl;
    }
    return dp[i][sum];
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 34, 4, 12, 5, 2 };
    System.out.println(count(a1, 9));

    int[] a2 = { 1, 1, 2, 3 };
    System.out.println(count(a2, 4));

    int[] a3 = { 1, 2, 7, 1, 5 };
    System.out.println(count(a3, 9));

  }
}
