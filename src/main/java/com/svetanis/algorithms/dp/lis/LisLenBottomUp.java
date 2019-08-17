package com.svetanis.algorithms.dp.lis;

import static java.lang.Math.max;
import static java.util.Arrays.fill;

//Given a number sequence, find the length 
//of its Longest Increasing Subsequence (LIS). 
//In an increasing subsequence, all the elements 
//are in increasing order (from lowest to highest).

public final class LisLenBottomUp {

  public static int lis(int[] a) {
    // Time Complexity: O(n^2)

    int n = a.length;
    int[] dp = new int[n];
    fill(dp, 1);

    int max = 1;
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i] && dp[i] <= dp[j]) {
          dp[i] = dp[j] + 1;
          max = max(max, dp[i]);
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 4, 2, 3, 6, 10, 1, 12 };
    System.out.println(lis(a1));
    
    int[] a2 = { -4, 10, 3, 7, 15 };
    System.out.println(lis(a2));

  }
}