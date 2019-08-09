package com.svetanis.algorithms.dp.coinchange;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

public final class MinNumOfCoinsDynamicTopDown {

  // Given a value V, if we want to make change for V cents,
  // and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
  // what is the minimum number of coins to make the change?

  // n - size of array of coins S
  // V - coin value

  private static final int V = 30;
  private static int[] M = new int[V + 1];

  public static int minCoins(int[] a, int j, int v) {
    int n = a.length;
    if (M[j] == v) {
      return M[j];
    }

    int min = MAX_VALUE;
    for (int i = 0; i < n; i++) {
      if (a[i] <= v) {
        int curr = minCoins(a, j + 1, v - a[i]);
        if (curr != MAX_VALUE) {
          min = min(min, curr + 1);
        }
      }
    }
    M[j] = min;
    return min;
  }

  public static void main(String[] args) {
    int v = 30;
    int[] a1 = { 25, 10, 5 };
    System.out.println(minCoins(a1, 0, v));
  }
}
