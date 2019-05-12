package com.svetanis.algorithms.dp.math.fib;

public final class FibonacciDynamicSpaceOptimII {

  public static int fib(int n) {
    
    if (n == 0) {
      return 0;
    } else if (n == 1 || n == 2) {
      return 1;
    }
    
    int fib1 = 1;
    int fib2 = 1;
    int fib = 1;
    for (int i = 3; i <= n; i++) {
      fib = fib1 + fib2;
      fib1 = fib2;
      fib2 = fib;
    }

    return fib;
  }

  public static void main(String[] args) {
    System.out.println(fib(8));
  }
}