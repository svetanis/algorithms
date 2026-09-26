package com.svetanis.algorithms.math.gcd;

// 1071. Greatest Common Divisor of Strings

// If both strings are copies of one block, gluing them in either order
// gives the same string: "ABCABC" + "ABC" and "ABC" + "ABCABC" are both
// "ABCABCABC". If s1 + s2 differs from s2 + s1, no block exists.
//
// When a block exists, the longest one has length gcd(len1, len2):
// "ABABAB" (6) and "ABAB" (4) -> gcd 2 -> "AB".

public final class GreatestCommonDivisorOfStrings1071 {
  // Time Complexity: O(n + m) for the two concatenations
  // Space Complexity: O(n + m)

  public static String gcd(String s1, String s2) {
    String cc1 = s1 + s2;
    String cc2 = s2 + s1;
    if (!cc1.equals(cc2)) {
      return "";
    }
    int n = s1.length();
    int m = s2.length();
    int len = gcd(n, m);
    return s1.substring(0, len);
  }

  private static int gcd(int a, int b) {
    if (a == 0) {
      return b;
    }
    return gcd(b % a, a);
  }

  public static void main(String[] args) {
    System.out.println(gcd("ABCABC", "ABC")); // ABC
    System.out.println(gcd("ABABAB", "ABAB")); // AB
    System.out.println(gcd("LEET", "CODE")); // ""
  }
}
