package com.svetanis.algorithms.recursive.count;

public final class ClimbingStairs {

  // n - total number of stairs
  // m - num of stairs person can climb at a time
  // returns num of ways to reach n stairs
  public static int count(int n, int m) {
    return countWays(n, m);
  }

  private static int countWays(int n, int m) {
    // Time complexity: exponential

    if (n < 0) {
      return 0;
    }

    if (n == 0) {
      return 1;
    }

    int count = 0;
    for (int i = 1; i <= m; i++) {
      count += countWays(n - i, m);
    }
    return count;
  }

  public static void main(String[] args) {
    int s = 4;
    System.out.println(count(s, 2));
  }
}
