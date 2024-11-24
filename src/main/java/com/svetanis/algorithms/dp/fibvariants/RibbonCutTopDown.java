package com.svetanis.algorithms.dp.fibvariants;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

public final class RibbonCutTopDown {

  public static int ribbonCut(int[] a, int v) {
    int[] dp = new int[v + 1];
    return ribbonCut(a, v, dp, 0);
  }

  public static int ribbonCut(int[] a, int v, int[] dp, int j) {
    int n = a.length;

    if (v == 0) {
      return 0;
    }

    if (v < 0) {
      return MIN_VALUE;
    }

    if (dp[j] == v) {
      return dp[j];
    }

    int max = MIN_VALUE;
    for (int i = 0; i < n; i++) {
      int cuts = ribbonCut(a, v - a[i], dp, j + 1);
      if (cuts != MIN_VALUE) {
        max = max(max, cuts + 1);
      }
    }
    dp[j] = max;
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
