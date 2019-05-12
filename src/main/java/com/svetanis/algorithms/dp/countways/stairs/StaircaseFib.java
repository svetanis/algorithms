package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseFib {

  public static int countWays(int n) {
    return fibOptimal(n + 1);
  }
  
  public static int fibOptimal(int n) {

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
    int s = 4;
    System.out.println(countWays(s));
  }
}
