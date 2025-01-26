package com.svetanis.algorithms.backtracking.permutations;

import static com.svetanis.java.base.utils.Print.print;

// Print all possible decimal strings of length n.

public final class PrintDecimalStrLenN {

  public static void generate(int n) {
    int[] a = new int[n];
    generate(n, a, 0);
  }

  private static void generate(int n, int[] a, int index) {
    if (index == n) {
      print(a);
      return;
    }

    for (int i = 0; i <= 9; i++) {
      a[index] = i;
      generate(n, a, index + 1);
    }
  }

  public static void main(String[] args) {
    generate(3);
  }
}
