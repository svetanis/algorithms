package com.svetanis.algorithms.string.reorder;

// 1768. Merge Strings Alternately
//
// Input: two strings s1 and s2. Returns the string that takes one letter from s1, then one from
// s2, and so on, and when one string runs out, appends the rest of the other. On LeetCode:
// 1 <= s1.length, s2.length <= 100, lowercase letters.
//
// The one idea: one index serves both strings. At index i, the letter s1[i] comes first and s2[i]
// second, each only if that string still has an index i. Going up to the longer length means the
// leftover of the longer string is appended one letter at a time without a separate step.
//
// Time: O(n + m) -- max(n, m) steps, each appends at most two letters.
// Space: O(n + m) -- the answer holds every letter of both strings.

public final class MergeStringsAlternately1768 {

  public static String merge(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    StringBuilder sb = new StringBuilder();
    for (int index = 0; index < n || index < m; index++) { // WALK: until the longer string ends
      if (index < n) {
        sb.append(s1.charAt(index)); // TAKE from s1 first, if it still has a letter
      }
      if (index < m) {
        sb.append(s2.charAt(index)); // TAKE from s2 second, if it still has a letter
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