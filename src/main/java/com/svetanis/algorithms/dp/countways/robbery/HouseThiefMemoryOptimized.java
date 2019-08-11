package com.svetanis.algorithms.dp.countways.robbery;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

// Given a number array representing 
// the wealth of ‘n’ houses, determine 
// the maximum amount of money the thief 
// can steal without alerting the security system.

public final class HouseThiefMemoryOptimized {

  public static int maxProfit(int[] a) {
    int n = a.length;
    int first = 0;
    int second = a[0];

    int max = MIN_VALUE;
    for (int i = 1; i < n; i++) {
      max = max(first + a[i], second);
      first = second;
      second = max;
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 2, 5, 1, 3, 6, 2, 4 };
    System.out.println(maxProfit(a1));

    int[] a2 = { 2, 10, 14, 8, 1 };
    System.out.println(maxProfit(a2));

  }
}
