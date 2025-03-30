package com.svetanis.algorithms.slidingwindow.fixed;

import static java.lang.Math.max;

public final class MaxProductSubarraySizeK {

  public static int maxProduct(int[] a, int k) {
    // Time Complexity: O(n)

    int n = a.length;
    int max = 1;
    for (int i = 0; i < k; i++) {
      max *= a[i];
    }
    int prev = max;

    for (int i = 0; i < n - k; i++) {
      int current = (prev / a[i]) * a[i + k];
      max = max(max, current);
      prev = current;
    }
    return max;
  }

  public static void main(String[] args) {
    int k = 6;
    int[] a1 = { 1, 5, 9, 8, 2, 4, 1, 8, 1, 2 };
    System.out.println(maxProduct(a1, k));

    k = 4;
    int[] a2 = { 1, 5, 9, 8, 2, 4, 1, 8, 1, 2 };
    System.out.println(maxProduct(a2, k));

    k = 3;
    int[] a3 = { 2, 5, 8, 1, 1, 3 };
    System.out.println(maxProduct(a3, k));
  }
}