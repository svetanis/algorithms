package com.svetanis.algorithms.math.gcd;

// 1071. Greatest Common Divisor of Strings

public final class GreatestCommonDivisorOfStrings1071 {
  // Time Complexity: O(n + m)
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
