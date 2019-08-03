package com.svetanis.algorithms.recursive.combinations;

import static com.svetanis.java.base.utils.Arrays.sum;
import static com.svetanis.java.base.utils.Print.print;

public final class AllSubSetsGivenSum {

  public static void subset(int[] a, int sum) {
    int n = a.length;
    int[] out = new int[n];
    subset(a, 0, out, 0, sum);
  }

  private static void subset(int[] in, int i, int[] out, int k, int sum) {
    if (i == in.length) {
      if (sum(out, 0, k - 1) == sum) {
        print(out, 0, k - 1);
      }
      return;
    }
    // exclude
    subset(in, i + 1, out, k, sum);
    // include
    out[k] = in[i];
    subset(in, i + 1, out, k + 1, sum);
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4, 5, 6 };
    subset(a, 10);
  }
}
