package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseRecursive {

  // Given a stair with ‘n’ steps,
  // implement a method to count
  // how many possible ways are there
  // to reach the top of the staircase,
  // given that, at every step you can
  // either take 1 step, 2 steps, or 3 steps.

  public static int count(int n) {
    // time complexity: O(3^n)

    // base case
    if (n < 0) {
      return 0;
    }
    if (n == 0 || n == 1) {
      return 1;
    }

    if (n == 2) {
      return 2;
    }

    int one = count(n - 1);
    int two = count(n - 2);
    int three = count(n - 3);
    return one + two + three;
  }

  public static void main(String[] args) {
    System.out.println(count(3));
  }
}
