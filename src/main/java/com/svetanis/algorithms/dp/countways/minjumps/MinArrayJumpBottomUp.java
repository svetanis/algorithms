package com.svetanis.algorithms.dp.countways.minjumps;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.fill;

public final class MinArrayJumpBottomUp {

  // Given an array of positive numbers,
  // where each element represents the max number of jumps
  // that can be made forward from that element,
  // write a program to find the minimum number of jumps
  // needed to reach the end of the array
  // (starting from the first element).
  // If an element is 0,
  // then we cannot move through that element.

  public static int count(int[] a) {
    int n = a.length;
    int[] dp = new int[n];
    fill(dp, MAX_VALUE);
    dp[0] = 0;
    for (int start = 0; start < n - 1; start++) {
      int jump = start + a[start];
      for (int end = start + 1; end <= jump && end < n; end++) {
        dp[end] = min(dp[end], dp[start] + 1);
      }
    }
    return dp[n - 1];
  }

  public static void main(String[] args) {
    int[] a1 = { 2, 1, 1, 1, 4 };
    System.out.println(count(a1));

    int[] a2 = { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
    System.out.println(count(a2));
  }
}
