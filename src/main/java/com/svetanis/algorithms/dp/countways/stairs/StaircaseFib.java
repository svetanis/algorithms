package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseFib {

  // Given a stair with ‘n’ steps,
  // implement a method to count
  // how many possible ways are there
  // to reach the top of the staircase,
  // given that, at every step you can
  // either take 1 step, 2 steps, or 3 steps.

  public static int countWays(int n) {
    return fibOptimal(n + 1);
  }

  public static int fibOptimal(int n) {

    if (n == 0) {
      return 0;
    }

    if (n == 1) {
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
    System.out.println(countWays(4));
  }
}
