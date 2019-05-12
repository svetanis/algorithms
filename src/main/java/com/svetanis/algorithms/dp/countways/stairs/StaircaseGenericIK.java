package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseGenericIK {

  // DYNAMIC
  public static long count(int n, int[] steps) {
    return countWays(n, steps);
  }

  private static long countWays(int n, int[] steps) {
    // Time complexity: O(nm)
    long[] a = new long[n + 1];
    a[0] = 1;
    for (int i = 1; i <= n; i++) {
      for (int step : steps) {
        if (i - step >= 0) {
          a[i] += a[i - step];
        }
      }
    }
    return a[n];
  }

  public static void main(String[] args) {
    int[] steps = { 2, 3 };
    System.out.println(count(7, steps));
  }
}
