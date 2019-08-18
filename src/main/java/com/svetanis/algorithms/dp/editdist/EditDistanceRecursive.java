package com.svetanis.algorithms.dp.editdist;

import static java.lang.Math.min;

public final class EditDistanceRecursive {

  public static int editDist(String x, String y) {
    // Time complexity: O(n * m)

    int n = x.length();
    int m = y.length();
    return editDist(x, y, n, m);
  }

  private static int editDist(String x, String y, int n, int m) {

    // base cases
    if (n == 0 && m == 0) {
      return 0;
    }
    if (n == 0) {
      return m;
    }
    if (m == 0) {
      return n;
    }

    if(x.charAt(n - 1) == y.charAt(m - 1)) {
      return editDist(x, y, n - 1, m - 1);
    }

    int delete = 1 + editDist(x, y, n - 1, m);
    int insert = 1 + editDist(x, y, n, m - 1);
    int replace = 1 + editDist(x, y, n - 1, m - 1);

    return min(min(delete, insert), replace);
  }

  public static void main(String[] args) {
    System.out.println(editDist("Zeil", "trials"));
    System.out.println(editDist("cat", "act"));
    System.out.println(editDist("COMBO", "COIN"));
  }
}
