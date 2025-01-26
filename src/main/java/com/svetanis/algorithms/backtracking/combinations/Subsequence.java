package com.svetanis.algorithms.backtracking.combinations;

// Given two strings str1 and str2, find if str1 is a subsequence of str2. 
// A subsequence is a sequence that can be derived from another sequence 
// by deleting some elements without changing the order of remaining elements

public final class Subsequence {

  public static boolean isSubSeq(String str1, String str2) {
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

  public static boolean isSubSeqIterative(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();

    if (n == 0) {
      return true;
    }

    if (m == 0) {
      return false;
    }

    int count = 0;
    for (int i = 0; i < m && count < n; i++) {
      if (s1.charAt(count) == s2.charAt(i)) {
        count++;
      }
    }
    return count == n;
  }

  public static void main(String[] args) {
    System.out.println(isSubSeq("AXY", "ADXCPY")); // true
    System.out.println(isSubSeq("AXY", "YADXCP")); // false
  }
}