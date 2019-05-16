package com.svetanis.algorithms.dp.math.permutationcoefficient;

public final class PermuteCoeffDPFactorial {

  // P(n, k) = n!/(n - k)!

  public static int permute(int n, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    int[] f = new int[n + 1];
    f[0] = 1;
    for (int i = 1; i <= n; i++) {
      f[i] = i * f[i - 1];
    }
    return f[n] / f[n - k];
  }

  public static void main(String[] args) {
    int n = 10;
    int k = 5;
    System.out.println(permute(n, k));
  }
}
