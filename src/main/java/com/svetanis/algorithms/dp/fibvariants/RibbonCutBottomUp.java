package com.svetanis.algorithms.dp.fibvariants;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.util.Arrays.fill;

public final class RibbonCutBottomUp {

  public static int ribbonCut(int[] a, int max) {
    int n = a.length;
    int[] dp = new int[max + 1];
    fill(dp, MIN_VALUE);
    dp[0] = 0;
    for (int v = 1; v <= max; v++) {
      int cuts = MIN_VALUE;
      for (int j = 0; j < n; j++) {
        if (a[j] <= v) {
          cuts = dp[v - a[j]];
        }
        if (cuts != MIN_VALUE) {
          dp[v] = max(dp[v], cuts + 1);
        }
      }
    }
    return dp[max];
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
