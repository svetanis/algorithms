package com.svetanis.algorithms.string.common;

// 14. Longest Common Prefix
// com.svetanis.algorithms.string.common.LongestCommonPrefix

public final class LongestCommonPrefix14 {
  // Time Complexity: O(n * m)

  public static String lcp(String[] a) {
    String prefix = a[0];
    for (int i = 1; i < a.length; i++) {
      prefix = lcp(a[i], prefix);
    }
    return prefix;
  }

  private static String lcp(String s, String prefix) {
    int n = s.length();
    int m = prefix.length();
    int index = 0;
    for (index = 0; index < Math.min(n, m); index++) {
      if (s.charAt(index) != prefix.charAt(index)) {
        break;
      }
    }
    return prefix.substring(0, index);
  }

  public static void main(String[] args) {
    String[] s1 = { "flower", "flow", "flight" };
    String[] s2 = { "dog", "racecar", "car" };
    System.out.println(lcp(s1));
    System.out.println(lcp(s2));
  }
}