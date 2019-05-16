package com.svetanis.algorithms.dp.math.pascal;

public final class PascalTriangleDynamicSpaceEfficient {

  public static void pascal(int n) {
    // Time complexity: O(n^2)
    // Auxiliary space: O(1)

    for (int line = 1; line <= n; ++line) {
      int c = 1; // used to represent C(line, i)
      for (int i = 1; i <= line; ++i) {
        // the first value in a line is always 1
        System.out.print(c + " ");
        c = c * (line - i) / i;
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int n = 7;
    pascal(n);
  }
}
