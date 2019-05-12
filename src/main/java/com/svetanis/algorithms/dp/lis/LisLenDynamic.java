package com.svetanis.algorithms.dp.lis;

import static com.svetanis.java.base.utils.Arrays.max;
import static java.lang.Math.max;
import static java.util.Arrays.fill;

public final class LisLenDynamic {

  public static int lis(int[] a) {
    // Time Complexity: O(n^2)

    int n = a.length;
    int[] len = new int[n];
    fill(len, 1);

    // compute optimized L values
    // in bottom up manner
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i]) {
          len[i] = max(len[i], len[j] + 1);
        }
      }
    }
    return max(len);
  }

  public static void main(String[] args) {
    int[] a = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
    System.out.println(lis(a));
  }
}