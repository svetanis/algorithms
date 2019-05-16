package com.svetanis.algorithms.dp.math.catalan;

import static com.svetanis.algorithms.dp.math.binomialcoefficient.BinomialEfficient.binom;

public final class CatalanBinomial {

  public static int catalan(int n) {
    // Time complexity: O(n)
    int c = binom(2 * n, n);
    return c / (n + 1);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(catalan(i));
    }
  }
}
