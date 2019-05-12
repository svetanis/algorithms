package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseDP {

  public static int count(int n) {
    int[] a = new int[30 + 1];
    for (int i = 0; i < a.length; ++i) {
      a[i] = -1;
    }
    return count(n, a);
  }

  private static int count(int n, int[] a) {
    if (n < 0) {
      return 0;
    } else if (n == 0) {
      return 1;
    } else if (a[n] > -1) {
      return a[n];
    } else {
      int one = count(n - 1, a);
      int two = count(n - 2, a);
      int three = count(n - 3, a);
      a[n] = one + two + three;
      return a[n];
    }
  }

  public static void main(String[] args) {
    int n = 3;
    System.out.println(count(n));
  }
}
