package com.svetanis.algorithms.dp.coins;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

public final class MinNumOfCoinsRecursive {

  // Given a value V, if we want to make change for V cents,
  // and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins,
  // what is the minimum number of coins to make the change?

  // n - size of array of coins S
  // V - coin value
  public static int minCoins(int[] a, int v) {

    int n = a.length;
    int min = MAX_VALUE;

    if (v == 0) {
      return 0;
    }

    for (int i = 0; i < n; i++) {
      if (a[i] <= v) {
        int current = minCoins(a, v - a[i]);
        if (current != MAX_VALUE) {
          min = min(min, current + 1);
        }
      }
    }
    return min;
  }

  public static void main(String[] args) {
    int[] a1 = { 25, 10, 5 };
    System.out.println(minCoins(a1, 30));

    int[] a2 = { 9, 6, 5, 1 };
    System.out.println(minCoins(a2, 11));
  }
}
