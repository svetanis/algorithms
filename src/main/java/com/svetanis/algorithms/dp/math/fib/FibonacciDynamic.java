package com.svetanis.algorithms.dp.math.fib;

public final class FibonacciDynamic {

  public static int fib(int n) {

    if (n == 0) {
      return 0;
    } else if (n == 1 || n == 2) {
      return 1;
    }

    int[] fib = new int[n + 1];
    fib[0] = 0;
    fib[1] = 1;
    fib[2] = 1;
    for (int i = 3; i <= n; i++) {
      fib[i] = fib[i - 1] + fib[i - 2];
    }
    return fib[n];
  }

  public static void main(String[] args) {
    System.out.println(fib(8));
  }
}