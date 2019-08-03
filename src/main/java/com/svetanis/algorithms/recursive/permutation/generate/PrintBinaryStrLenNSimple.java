package com.svetanis.algorithms.recursive.permutation.generate;

import static com.svetanis.java.base.utils.Print.print;

// Print all possible binary strings of length n.

public final class PrintBinaryStrLenNSimple {

  public static void generate(int n) {
    int[] a = new int[n];
    generate(n, a);
  }

  private static void generate(int n, int[] a) {
    if (n <= 0) {
      print(a);
      return;
    }

    a[n - 1] = 0;
    generate(n - 1, a);

    a[n - 1] = 1;
    generate(n - 1, a);
  }

  public static void main(String[] args) {
    generate(3);
  }
}
