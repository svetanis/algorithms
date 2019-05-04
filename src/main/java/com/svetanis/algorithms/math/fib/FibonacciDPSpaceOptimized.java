package com.svetanis.algorithms.math.fib;

public final class FibonacciDPSpaceOptimized {

  public static int fib(int n) {

    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }

    int fib1 = 0;
    int fib2 = 1;
    for (int i = 2; i <= n; i++) {
      int fib = fib1 + fib2;
      fib1 = fib2;
      fib2 = fib;
    }
    return fib2;
  }

  public static void main(String[] args) {
    System.out.println(fib(8));
  }
}