package com.svetanis.algorithms.sorting.mergesort;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public final class MinimizeDifference {

  public static int minimize(int[] a, int[] b, int[] c) {
    int i = a.length - 1;
    int j = b.length - 1;
    int k = c.length - 1;

    int min = minDiff(a[i], b[j], c[k]);
    while (i != -1 && j != -1 && k != -1) {
      int diff = minDiff(a[i], b[j], c[k]);
      min = min(min, diff);
      int max = max(a[i], max(b[j], c[k]));
      if (a[i] == max) {
        i--;
      } else if (b[j] == max) {
        j--;
      } else {
        k--;
      }
    }
    return min;
  }

  private static int minDiff(int a, int b, int c) {
    int max = max(a, max(b, c));
    int min = min(a, min(b, c));
    return abs(max - min);
  }

  public static void main(String[] args) {
    int[] a = { 11, 4, 5, 8, 10 };
    int[] b = { 6, 9, 11 };
    int[] c = { 2, 3, 16, 6 };
    System.out.println(minimize(a, b, c));

    int[] d = { 5, 8, 10, 15 };
    int[] e = { 6, 9, 15, 78, 89 };
    int[] f = { 2, 3, 6, 6, 8, 8, 10 };
    System.out.println(minimize(d, e, f));
  }
}
