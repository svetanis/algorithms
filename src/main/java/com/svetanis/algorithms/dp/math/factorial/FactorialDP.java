package com.svetanis.algorithms.dp.math.factorial;

public final class FactorialDP {

  public static int factorial(int n) {
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    int[] f = new int[n + 1];
    f[0] = 1;
    for (int i = 1; i <= n; i++) {
      f[i] = i * f[i - 1];
    }
    return f[n];
  }

  public static void main(String[] args) {
    System.out.println(factorial(5));
  }
}
