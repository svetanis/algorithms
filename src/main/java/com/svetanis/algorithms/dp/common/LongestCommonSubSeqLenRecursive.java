package com.svetanis.algorithms.dp.common;

import static java.lang.Math.max;

public final class LongestCommonSubSeqLenRecursive {

  public static int lcs(String s1, String s2) {
    // Time Complexity: O(2^n)

    int n = s1.length();
    int m = s2.length();
    return lcs(s1, s2, n, m);
  }

  private static int lcs(String s1, String s2, int n, int m) {
    if (n == 0 || m == 0) {
      return 0;
    } else if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      int diagonal = lcs(s1, s2, n - 1, m - 1);
      return 1 + diagonal;
    } else {
      int top = lcs(s1, s2, n - 1, m);
      int left = lcs(s1, s2, n, m - 1);
      return max(left, top);
    }
  }

  public static void main(String[] args) {
    String s1 = "AGGTAB";
    String s2 = "GXTXAYB";
    System.out.println(lcs(s1, s2));
  }
}
