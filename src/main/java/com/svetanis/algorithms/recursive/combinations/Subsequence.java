package com.svetanis.algorithms.recursive.combinations;

public final class Subsequence {

  public static boolean isSubsequence(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    return isSubSeq(str1, str2, n, m);
  }

  private static boolean isSubSeq(String s1, String s2, int n, int m) {

    if (n == 0) {
      return true;
    }

    if (m == 0) {
      return false;
    }

    // if chars of two strings are matching
    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      return isSubSeq(s1, s2, n - 1, m - 1);
    }

    // if last chars are not matching
    return isSubSeq(s1, s2, n, m - 1);
  }

  public static void main(String[] args) {
    System.out.println(isSubsequence("AXY", "ADXCPY")); // true
    System.out.println(isSubsequence("AXY", "YADXCP")); // false
  }
}