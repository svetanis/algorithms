package com.svetanis.algorithms.dp.countways.robbery;

import static java.lang.Math.max;

// Given a number array representing 
// the wealth of ‘n’ houses, determine 
// the maximum amount of money the thief 
// can steal without alerting the security system.
// all houses at this place are arranged in a circle. 
// That means the first house is the neighbor of the last one.
// There are two cases here 
// 1) 1st element is included and last is not included 
// 2) 1st is not included and last is included. 
// Therefore, we can use the similar dynamic programming approach 
// to scan the array twice and get the larger value.

public final class HouseThiefCircularRecursive {

  public static int maxProfit(int[] a) {
    int n = a.length;
    int incl = maxProfit(a, 0, n - 2);
    int excl = maxProfit(a, 1, n - 1);
    return max(incl, excl);
  }

  private static int maxProfit(int[] a, int start, int end) {
    if (start >= end) {
      return 0;
    }

    // include
    int incl = a[start] + maxProfit(a, start + 2, end);
    // exclude
    int excl = maxProfit(a, start + 1, end);
    return max(incl, excl);
  }

  public static void main(String[] args) {
    int[] a1 = { 2, 5, 1, 3, 6, 2, 4 };
    System.out.println(maxProfit(a1));

    int[] a2 = { 2, 10, 14, 8, 1 };
    System.out.println(maxProfit(a2));

  }
}
