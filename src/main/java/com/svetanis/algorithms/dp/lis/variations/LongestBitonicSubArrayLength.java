package com.svetanis.algorithms.dp.lis.variations;

import static java.lang.Math.max;

public final class LongestBitonicSubArrayLength {

  public static int lbs(int[] a) {
    // Time complexity: O(n) ;
    // Auxiliary space: O(n)

    int[] inc = incr(a);
    int[] dec = decr(a);
    return getMaxLen(inc, dec);
  }

  private static int[] incr(int[] a) {
    int n = a.length;
    int[] inc = new int[n];
    // length of increasing sequence
    // ending at first index is 1
    inc[0] = 1;
    // 1. construct increasing sequence array
    for (int i = 1; i < n; ++i) {
      if (a[i] > a[i - 1]) {
        inc[i] = inc[i - 1] + 1;
      } else {
        inc[i] = 1;
      }
    }
    return inc;
  }

  private static int[] decr(int[] a) {
    int n = a.length;
    int[] dec = new int[n];
    // length of decreasing sequence
    // starting at last index is 1
    dec[n - 1] = 1;
    // 2. construct decreasing sequence array
    for (int i = n - 2; i >= 0; --i) {
      if (a[i] > a[i + 1]) {
        dec[i] = dec[i + 1] + 1;
      } else {
        dec[i] = 1;
      }
    }
    return dec;
  }

  private static int getMaxLen(int[] inc, int[] dec) {
    int n = inc.length;
    // 3. find the length of max length bitonic sequence
    int max = inc[0] + dec[0] - 1;
    for (int i = 1; i < n; ++i) {
      max = max(max, inc[i] + dec[i] - 1);
    }
    return max;
  }

  public static void main(String[] args) {
    int arr[] = { 12, 4, 78, 90, 45, 23 };
    System.out.println("Max length size: " + lbs(arr));
  }
}
