package com.svetanis.algorithms.dp.lis;

import static java.lang.Math.max;

public final class LisLenRecursive {

  public static int lis(int[] a) {
    int n = a.length;
    return lis(a, n);
  }

  private static int lis(int[] a, int n) {
    // base case
    if (n == 1) {
      return 1;
    }

    int max = 1;

    // recursively get all LIS ending
    // with a[0], a[1] ... a[n-2].
    // if a[i - 1] is smaller than a[n-1],
    // and max ending with a[n-1]
    // needs to be updated, then update it
    for (int i = 1; i < n; ++i) {
      int len = lis(a, i);
      if (a[i - 1] < a[n - 1]) {
        max = max(max, len + 1);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
    System.out.println(lis(a));
  }
}