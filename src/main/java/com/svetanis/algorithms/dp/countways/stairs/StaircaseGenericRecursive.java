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
    int result = 0;
    for (int i = 1; i <= m && i <= n; i++) {
      result += countWays(n - i, m);
    }
    return result;
  }

  public static void main(String[] args) {
    int s = 4;
    System.out.println(count(s, 2));
  }
}
