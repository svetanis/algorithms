package com.svetanis.algorithms.dp.sum.max.subseq;

import static java.lang.Math.max;
import static java.lang.System.arraycopy;

// Given a number sequence, 
// find the increasing subsequence with the highest sum. 
// Write a method that returns the highest sum.

public final class MaxSumIncreasingSubSeqLenBottomUp {

// L[0] = {a[0]}
// L[i] = {MaxSum(L[j])} + a[i], j < i, a[j] < a[i]
//      = a[i], if there is no j such that a[j] < a[i] 
  
  public static int maxSum(int[] a) {
    // Time Complexity: O(n^2)

    int n = a.length;
    int[] dp = new int[n];
    arraycopy(a, 0, dp, 0, n);
    int max = a[0];
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[i] > a[j]) {
          dp[i] = max(dp[i], dp[j] + a[i]);
        }
      }
      max = max(max, dp[i]);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 101, 2, 3, 100, 4, 5 };
    System.out.println(maxSum(a1)); // 106

    int[] a2 = { 3, 4, 5, 10 };
    System.out.println(maxSum(a2)); // 22

    int[] a3 = { 10, 5, 4, 3 };
    System.out.println(maxSum(a3)); // 10
  }
}
