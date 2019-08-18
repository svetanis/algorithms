package com.svetanis.algorithms.dp.lis.variations;

import static java.lang.Math.max;

// Given a number sequence, find the length 
// of its Longest Alternating Subsequence (LAS). 
// A subsequence is considered alternating 
// if its elements are in alternating order.

public final class LongestAlternatingSubSeqLenTopDown {

  public static int las(int[] a) {
    // Time Complexity : O(n^2)
    // Auxiliary space: O(n)
    int n = a.length;
    int[][] dp = new int[n][2];
    int asc = las(a, dp, 1, 1);
    int des = las(a, dp, 1, 0);
    return 1 + max(asc, des);
  }

  private static int las(int[] a, int[][] dp, int start, int asc) {

    int n = a.length;
    if(start >= n) {
      return 0;
    }
    
    if (dp[start][asc] != 0) {
      return dp[start][asc];
    }

    int max = 0;
    for (int i = start; i < n; i++) {
      if (asc == 1 && a[i - 1] < a[i]) {
        max = max(max, 1 + las(a, dp, i + 1, 0));
      } else if (asc == 0 && a[i - 1] > a[i]) {
        max = max(max, 1 + las(a, dp, i + 1, 1));
      } else {
        max = max(max, las(a, dp, i + 1, asc));
      }
    }
    dp[start][asc] = max;
    return dp[start][asc];
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 3, 4 };
    System.out.println(las(a1));

    int[] a2 = { 3, 2, 1, 4 };
    System.out.println(las(a2));

    int[] a3 = { 1, 3, 2, 4 };
    System.out.println(las(a3));
  }
}
