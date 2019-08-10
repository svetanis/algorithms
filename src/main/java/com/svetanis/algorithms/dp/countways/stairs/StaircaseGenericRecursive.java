package com.svetanis.algorithms.dp.countways.stairs;

public final class StaircaseGenericRecursive {

  // n - total number of stairs
  // m - num of stairs person can climb at a time
  // returns num of ways to reach n stairs
  public static int count(int n, int m) {
    return countWays(n + 1, m);
  }

  private static int countWays(int n, int m) {
    // Time complexity: exponential

    if (n <= 1) {
      return n;
    }
    int count = 0;
    for (int i = 1; i <= m && i <= n; i++) {
      count += countWays(n - i, m);
    }
    return count;
  }

  public static void main(String[] args) {
    System.out.println(count(4, 2));
  }
}
