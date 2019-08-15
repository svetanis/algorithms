package com.svetanis.algorithms.dp.common;

import static java.lang.Math.max;

// Given two strings ‘s1’ and ‘s2’, 
// find the length of the longest substr 
// which is common in both the strings.

public final class LongestCommonSubStrLenRecursive {

  public static int lcs(String s1, String s2) {
    return lcs(s1, s2, 0, 0, 0);
  }

  private static int lcs(String s1, String s2, int i, int j, int max) {
    int n = s1.length();
    int m = s2.length();

    if (i == n || j == m) {
      return max;
    }

    if (s1.charAt(i) == s2.charAt(j)) {
      max = lcs(s1, s2, i + 1, j + 1, max + 1);
    }

    int first = lcs(s1, s2, i, j + 1, 0);
    int second = lcs(s1, s2, i + 1, j, 0);
    return max(max, max(first, second));
  }

  public static void main(String[] args) {
    System.out.println(lcs("abdca", "cbda"));
    System.out.println(lcs("passport", "ppsspt"));
  }
}