package com.svetanis.algorithms.dp.common;

import static java.lang.Math.max;

public final class LongestRepeatedSubSeqLenRecursive {

  public static int lcs(String str) {
    // Time Complexity: O(2^n)

    return lcs(str, 0, 0);
  }

  private static int lcs(String s, int i, int j) {
    int n = s.length();

    if (i == n || j == n) {
      return 0;
    }
    if (i != j && s.charAt(i) == s.charAt(j)) {
      return 1 + lcs(s, i + 1, j + 1);
    }
    int top = lcs(s, i + 1, j);
    int left = lcs(s, i, j + 1);
    return max(left, top);
  }

  public static void main(String[] args) {
    System.out.println(lcs("tomorrow"));
    System.out.println(lcs("aabdbcec"));
    System.out.println(lcs("fmff"));
  }
}
