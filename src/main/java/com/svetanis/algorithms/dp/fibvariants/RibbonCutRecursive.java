package com.svetanis.algorithms.dp.fibvariants;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

public final class RibbonCutRecursive {

  public static int ribbonCut(int[] a, int v) {

    int n = a.length;

    if (v == 0) {
      return 0;
    }

    if (v < 0) {
      return MIN_VALUE;
    }

    int max = MIN_VALUE;
    for (int i = 0; i < n; i++) {
      int cuts = ribbonCut(a, v - a[i]);
      if (cuts != MIN_VALUE) {
        max = max(max, cuts + 1);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 2, 3, 5 };
    System.out.println(ribbonCut(a1, 5));

    int[] a2 = { 2, 3 };
    System.out.println(ribbonCut(a2, 7));

    int[] a3 = { 3, 5, 7 };
    System.out.println(ribbonCut(a3, 13));

    int[] a4 = { 3, 5 };
    System.out.println(ribbonCut(a4, 7));
  }
}
