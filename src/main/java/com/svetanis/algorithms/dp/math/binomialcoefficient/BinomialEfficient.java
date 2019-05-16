package com.svetanis.algorithms.dp.math.binomialcoefficient;

public final class BinomialEfficient {

  public static int binom(int n, int k) {
    // Time complexity: O(k)
    // Space complexity: O(1)

    // Since C(n, k) = C(n, n - k)
    if (k > n - k) {
      k = n - k;
    }

    // Calculate value of
    // [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
    int result = 1;
    for (int i = 0; i < k; ++i) {
      result *= (n - i);
      result /= (i + 1);
    }
    return result;
  }

  public static void main(String[] args) {
    int n = 8;
    int k = 2;
    System.out.println(binom(n, k));
  }
}
