package com.svetanis.algorithms.dp.lis;

import static java.lang.Math.max;

// Given a number sequence, find the length 
// of its Longest Increasing Subsequence (LIS). 
// In an increasing subsequence, all the elements 
// are in increasing order (from lowest to highest).

public final class LisLenTopDown {

  public static int lis(int[] a) {
    int n = a.length;
    int[][] dp = new int[n][n + 1];
    return lis(a, dp, 0, -1);
  }

  private static int lis(int[] a, int[][] dp, int index, int prev) {
    int n = a.length;

    // base case
    if (index == n) {
      return 0;
    }

    if (dp[index][prev + 1] != 0) {
      return dp[index][prev + 1];
    }

    // include
    int incl = 0;
    if (prev == -1 || a[index] > a[prev]) {
      incl = 1 + lis(a, dp, index + 1, index);
    }

    // exclude
    int excl = lis(a, dp, index + 1, prev);
    dp[index][prev + 1] = max(incl, excl);
    return dp[index][prev + 1];
  }

  public static void main(String[] args) {
    int[] a = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
    System.out.println(lis(a)); // 6
  }
}