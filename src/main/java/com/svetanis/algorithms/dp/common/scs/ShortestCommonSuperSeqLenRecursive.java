package com.svetanis.algorithms.dp.common.scs;

import static java.lang.Math.min;

// Given two sequences ‘s1’ and ‘s2’, 
// write a method to find the len of 
// the shortest sequence which has 
// ‘s1’ and ‘s2’ as subsequences.

public final class ShortestCommonSuperSeqLenRecursive {

  public static int scs(String s1, String s2) {
    // Time Complexity: O(2^min(n, m))

    int n = s1.length();
    int m = s2.length();
    return scs(s1, s2, n, m);
  }

  private static int scs(String s1, String s2, int n, int m) {

    if (n == 0 || m == 0) {
      return n + m;
    }

    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      return 1 + scs(s1, s2, n - 1, m - 1);
    }

    int first = scs(s1, s2, n - 1, m);
    int second = scs(s1, s2, n, m - 1);
    return 1 + min(first, second);
  }

  public static void main(String[] args) {
    String s1 = "geek";
    String s2 = "eke";
    System.out.println(scs(s1, s2));

    String s3 = "AGGTAB";
    String s4 = "GXTXAYB";
    System.out.println(scs(s3, s4));
  }
}
