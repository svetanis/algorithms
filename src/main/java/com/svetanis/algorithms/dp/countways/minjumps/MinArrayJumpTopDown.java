package com.svetanis.algorithms.dp.countways.minjumps;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

public final class MinArrayJumpTopDown {

  // Given an array of positive numbers,
  // where each element represents the max number of jumps
  // that can be made forward from that element,
  // write a program to find the minimum number of jumps
  // needed to reach the end of the array
  // (starting from the first element).
  // If an element is 0,
  // then we cannot move through that element.

  public static int count(int[] a) {
    int[] dp = new int[a.length];
    return count(a, dp, 0);
  }

  private static int count(int[] a, int[] dp, int index) {
    int n = a.length;
    
    if (index == n - 1) {
      return 0;
    }

    if (a[index] == 0) {
      return MAX_VALUE;
    }
    
    if(dp[index] != 0) {
      return dp[index];
    }

    int min = MAX_VALUE;
    int start = index + 1;
    int end = index + a[index];
    while (start < n && start <= end) {
      int jumps = count(a, dp, start);
      if (jumps != MAX_VALUE) {
        min = min(min, jumps + 1);
      }
      start++;
    }
    dp[index] = min;
    return dp[index];
  }

  public static void main(String[] args) {
    int[] a1 = { 2, 1, 1, 1, 4 };
    System.out.println(count(a1));

    int[] a2 = { 1, 1, 3, 6, 9, 3, 0, 1, 3 };
    System.out.println(count(a2));
  }
}
