package com.svetanis.algorithms.dp.math.permutationcoefficient;

import static java.lang.Math.min;

public final class PermuteCoeffDynamic {

  // P(n, k) = P(n - 1, k) + k * P(n - 1, k - 1)

  public static int permute(int n, int k) {
    // Time Complexity: n * k
    // Space Complexity: n * k

    int[][] p = new int[n + 1][k + 1];
    p[0][0] = 1;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= min(n, k); j++) {
        if (j == 0) {
          p[i][j] = 1;
        } else {
          p[i][j] = p[i - 1][j] + j * p[i - 1][j - 1];
        }
      }
    }
    return p[n][k];
  }

  public static void main(String[] args) {
    int n = 10;
    int k = 5;
    System.out.println(permute(n, k));
  }
}
