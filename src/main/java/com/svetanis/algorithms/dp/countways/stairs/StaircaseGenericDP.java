package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseGenericDP {

  // DYNAMIC
  public static int count(int n, int m) {
    return countWays(n + 1, m);
  }

  private static int countWays(int n, int m) {
    // Time complexity: O(nm)

    int[] a = new int[n];
    a[0] = 1;
    a[1] = 1;
    for (int i = 2; i < n; i++) {
      a[i] = 0;
      for (int j = 1; j <= m && j <= i; j++) {
        a[i] += a[i - j];
      }
    }
    return a[n - 1];
  }

  public static void main(String[] args) {
    int s = 4;
    System.out.println(count(s, 2));
  }
}
