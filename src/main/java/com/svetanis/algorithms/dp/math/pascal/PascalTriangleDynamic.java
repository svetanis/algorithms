package com.svetanis.algorithms.dp.math.pascal;

public final class PascalTriangleDynamic {

  public static void pascal(int n) {
    // Time complexity: O(n^2)
    // Auxilary space: O(n^2)

    // an auxiliary array to store
    // generated pascal triangle
    int[][] m = new int[n][n];

    // iterate through every line
    // and print integers in it
    for (int line = 0; line < n; ++line) {
      // every line has number of
      // integers equal to line number
      for (int i = 0; i <= line; ++i) {
        // first and last values
        // in every row are 1
        if (i == 0 || i == line) {
          m[line][i] = 1;
        } else {
          // other values are sum of values
          // just above and left of above
          m[line][i] = m[line - 1][i - 1] + m[line - 1][i];
        }
        System.out.print(m[line][i] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int n = 7;
    pascal(n);
  }
}
