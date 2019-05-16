package com.svetanis.algorithms.dp.math.binomialcoefficient;

import static java.lang.Math.min;

public final class BinomialDP {

  public static int binom(int n, int k) {
    // Time complexity: O(n * k)
    // Space complexity: O(n * k)

    int[][] c = new int[n + 1][k + 1];
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= min(i, k); ++j) {
        // base cases
        if (j == 0 || j == i) {
          c[i][j] = 1;
        } else {
          c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
        }
      }
    }
    return c[n][k];
  }

  public static void main(String[] args) {
    int n = 8;
    int k = 2;
    System.out.println(binom(n, k));
  }
}
