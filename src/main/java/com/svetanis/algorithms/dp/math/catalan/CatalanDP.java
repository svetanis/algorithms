package com.svetanis.algorithms.dp.math.catalan;

public final class CatalanDP {

  // dynamic
  public static int catalan(int n) {
    int[] a = new int[n + 1];
    a[0] = 1;
    if (n != 0) {
      a[1] = 1;
    }
    for (int i = 2; i <= n; i++) {
      a[i] = 0;
      for (int j = 0; j < i; j++) {
        a[i] += a[j] * a[i - j - 1];
      }
    }
    return a[n];
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(catalan(i));
    }
  }
}
