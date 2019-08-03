package com.svetanis.algorithms.recursive.permutation.generate;

import static com.svetanis.java.base.utils.Print.print;

// Print all possible binary strings of length n.

public final class PrintBinaryStrLenN {

  public static void generate(int n) {
    int[] a = new int[n];
    generate(n, a, 0);
  }

  private static void generate(int n, int[] a, int i) {
    if (i == n) {
      print(a);
      return;
    }

    a[i] = 0;
    generate(n, a, i + 1);

    a[i] = 1;
    generate(n, a, i + 1);
  }

  public static void main(String[] args) {
    generate(3);
  }
}
