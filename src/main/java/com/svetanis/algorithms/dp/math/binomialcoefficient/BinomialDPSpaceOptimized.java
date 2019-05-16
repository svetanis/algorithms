package com.svetanis.algorithms.dp.math.binomialcoefficient;

import static java.lang.Math.min;

public final class BinomialDPSpaceOptimized {

  public static int binom(int n, int k) {
    // Time complexity: O(n * k)
    // Space complexity: O(k)

    int[] c = new int[k + 1];
    c[0] = 1;
    for (int i = 1; i <= n; ++i) {
      for (int j = min(i, k); j > 0; --j) {
        // Compute next row of pascal
        // triangle using the previous row
        c[j] = c[j] + c[j - 1];
      }
    }
    return c[k];
  }

  public static void main(String[] args) {
    int n = 8;
    int k = 2;
    System.out.println(binom(n, k));
  }
}
