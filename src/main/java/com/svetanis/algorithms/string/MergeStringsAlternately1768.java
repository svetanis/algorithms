package com.svetanis.algorithms.string;

// 1768. Merge Strings Alternately

public final class MergeStringsAlternately1768 {
  // Time Complexity: O(max(n,m))
  // Space Complexity: O(max(n,m))

  public static String merge(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    StringBuilder sb = new StringBuilder();
    for (int index = 0; index < n || index < m; index++) {
      if (index < n) {
        sb.append(s1.charAt(index));
      }
      if (index < m) {
        sb.append(s2.charAt(index));
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(merge("abc", "pqr")); // apbqcr
    System.out.println(merge("ab", "pqrs")); // apbqrs
    System.out.println(merge("abcd", "pq")); // apbqcd
  }
}