package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseGenericBottomUp {

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
    System.out.println(count(4, 2));
  }
}
