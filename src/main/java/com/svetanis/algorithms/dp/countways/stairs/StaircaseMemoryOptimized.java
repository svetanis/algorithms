package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseMemoryOptimized {

  // Given a stair with ‘n’ steps,
  // implement a method to count
  // how many possible ways are there
  // to reach the top of the staircase,
  // given that, at every step you can
  // either take 1 step, 2 steps, or 3 steps.

  public static int count(int n) {

    if (n < 2) {
      return 1;
    }

    if (n == 2) {
      return 2;
    }

    int first = 1;
    int second = 1;
    int third = 2;
    for (int i = 3; i <= n; i++) {
      int temp = first + second + third;
      first = second;
      second = third;
      third = temp;
    }
    return third;
  }

  public static void main(String[] args) {
    System.out.println(count(3));
  }
}
